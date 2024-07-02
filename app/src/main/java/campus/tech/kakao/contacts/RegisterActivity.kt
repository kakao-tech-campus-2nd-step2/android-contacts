package campus.tech.kakao.contacts

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RegisterActivity : AppCompatActivity() {
    lateinit var registerBtnLayout: FrameLayout
    lateinit var contactRecyclerView: RecyclerView
    lateinit var howToRegisterTextView: TextView
    private lateinit var startActivityLauncher: ActivityResultLauncher<Intent>
    private var contactList = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        setOnClickListeners()
        setContactRecyclerView()
        setStartActivityLauncher()
        restoreInstanceState(savedInstanceState)
    }

    /**
     * 사용할 view들을 초기화하는 함수
     *
     * - `registerBtnLayout` : 연락처 등록 버튼을 나타내는 FrameLayout
     * - `contactRecyclerView` : 연락처 목록을 나타내는 RecyclerView
     * - `howToRegisterTextView` : 연락처 등록 방법 메시지를 나타내는 TextView
     */
    private fun initViews() {
        registerBtnLayout = findViewById(R.id.register_btn_layout)
        contactRecyclerView = findViewById(R.id.contact_recycler_view)
        howToRegisterTextView = findViewById(R.id.how_to_register_text_view)
    }

    /**
     * 사용할 클릭 리스너들을 설정하는 함수
     */
    private fun setOnClickListeners() {
        setOnClickListenerOfRegisterBtnLayout()
    }

    /**
     * 연락처 추가 버튼에 대한 클릭 리스너를 설정하는 함수
     *
     * 클릭 시 MainActivity로 넘어가고 Contact 객체를 결과로 받는 것을 기다림.
     */
    private fun setOnClickListenerOfRegisterBtnLayout() {
        registerBtnLayout.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivityLauncher.launch(intent)
        }
    }

    /**
     * 연락처 리사이클러뷰를 설정하는 함수
     *
     * - `contactClickListener` : RecyclerView의 아이템이 클릭될 때 실행될 코드를 포함하는 OnContactClickListener 구현 객체
     *
     */
    private fun setContactRecyclerView() {
        val contactClickListener =
            object : OnContactClickListener {
                override fun onContactClicked(position: Int) {
                    val contact = contactList[position]
                    val intent =
                        Intent(this@RegisterActivity, DetailActivity::class.java).apply {
                            putExtra("contact", contact)
                        }
                    startActivity(intent)
                }
            }
        contactRecyclerView.adapter =
            ContactRecyclerViewAdapter(contactList, contactClickListener)
        contactRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    /**
     * startActivityLauncher를 설정하는 함수
     *
     * result로 contact 객체를 받아와 list에 추가.
     * 하나 이상의 contact 객체가 들어오면 등록 안내 textview의 visibility를 gone으로 설정.
     */
    @SuppressLint("NotifyDataSetChanged")
    private fun setStartActivityLauncher() {
        startActivityLauncher =
            registerForActivityResult(StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val contact: Contact? =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            result.data?.getParcelableExtra("CONTACT_RESULT", Contact::class.java)
                        } else {
                            result.data?.getParcelableExtra("CONTACT_RESULT")
                        }
                    contact?.let {
                        contactList.add(it)
                        if (howToRegisterTextView.visibility != View.GONE) {
                            howToRegisterTextView.visibility = View.GONE
                        }
                        contactRecyclerView.adapter?.notifyDataSetChanged()
                    }
                }
            }
    }

    interface OnContactClickListener {
        fun onContactClicked(position: Int)
    }

    class ContactRecyclerViewAdapter(
        private val contactList: ArrayList<Contact>,
        private val contactClickListener: OnContactClickListener,
    ) : RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val lastNameTextView: TextView = itemView.findViewById(R.id.last_name_text_view)
            private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)

            fun bind(
                contact: Contact,
                clickListener: OnContactClickListener,
            ) {
                setLastNameText(contact.name[0].toString())
                setNameText(contact.name)
                itemView.setOnClickListener {
                    clickListener.onContactClicked(adapterPosition)
                }
            }

            private fun setLastNameText(lastName: String) {
                lastNameTextView.text = lastName
            }

            private fun setNameText(name: String) {
                nameTextView.text = name
            }
        }

        @Override
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
        ): ViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.contact_item, parent, false)
            return ViewHolder(itemView)
        }

        @Override
        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int,
        ) {
            val contact = contactList[position]
            holder.bind(contact, contactClickListener)
        }

        @Override
        override fun getItemCount(): Int {
            return contactList.size
        }
    }

    /**
     * 화면 방향 전환 등으로 인해 Activity의 정보가 사라지지 않도록 저장하는 함수
     *
     * @param outState Activity의 현재 상태를 저장하는 Bundle 객체.
     */
    @Override
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("contact_list", ArrayList(contactList))
    }

    /**
     * 화면 방향 전환 등으로 인해 Activity의 정보가 사라지지 않도록 복원하는 함수
     *
     * @param savedInstanceState onSaveInstanceState에서 저장된 데이터를 포함하는 Bundle 객체.
     */
    @Override
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        contactList = savedInstanceState.getParcelableArrayList("contact_list")!!
    }

    companion object {
        const val KEY_CONTACT_LIST = "contact_list"
    }

    /**
     * 화면 방향 전환 등으로 인해 Activity의 정보가 사라지지 않도록 복원하는 함수
     *
     * @param savedInstanceState onSaveInstanceState에서 저장된 데이터를 포함하는 Bundle 객체.
     */
    @Override
    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            contactList = savedInstanceState.getParcelableArrayList(KEY_CONTACT_LIST) ?: ArrayList()
            setContactRecyclerView()
            if (contactList.isNotEmpty()) {
                howToRegisterTextView.visibility = View.GONE
            }
        }
    }
}
