package campus.tech.kakao.contacts

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
    private val contactList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        setOnClickListeners()
        setContactRecyclerView()
        setStartActivityLauncher()
    }

    private fun initViews() {
        registerBtnLayout = findViewById(R.id.register_btn_layout)
        contactRecyclerView = findViewById(R.id.contact_recycler_view)
        howToRegisterTextView = findViewById(R.id.how_to_register_text_view)
    }

    private fun setOnClickListeners() {
        setOnClickListenerOfRegisterBtnLayout()
    }

    private fun setOnClickListenerOfRegisterBtnLayout() {
        registerBtnLayout.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivityLauncher.launch(intent)
        }
    }

    private fun setContactRecyclerView() {
        contactRecyclerView.adapter =
            ContactRecyclerViewAdapter(contactList, LayoutInflater.from(this))
        contactRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setStartActivityLauncher() {
        startActivityLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val contact: Contact? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra("CONTACT_RESULT", Contact::class.java)
                } else {
                    result.data?.getParcelableExtra("CONTACT_RESULT")
                }
                contact?.let {
                    contactList.add(it)
                    howToRegisterTextView.visibility = View.GONE
                    contactRecyclerView.adapter?.notifyDataSetChanged()

                }
            }
        }
    }

    class ContactRecyclerViewAdapter(
        var contactList: MutableList<Contact>,
        var inflater: LayoutInflater
    ) : RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val lastNameTextView: TextView
            val nameTextView: TextView

            init {
                lastNameTextView = itemView.findViewById(R.id.last_name_text_view)
                nameTextView = itemView.findViewById(R.id.name_text_view)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.contact_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return contactList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.lastNameTextView.text = contactList.get(position).name.get(0).toString()
            holder.nameTextView.text = contactList.get(position).name
        }
    }

}