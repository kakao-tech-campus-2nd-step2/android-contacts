package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    lateinit var resultLauncher : ActivityResultLauncher<Intent>
    lateinit var descriptionText : TextView
    lateinit var plusButton : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        val contactList = mutableListOf<Contact>()

        plusButton = findViewById<TextView>(R.id.plus_item_button)
        descriptionText = findViewById<TextView>(R.id.description_text)
        plusButton.setOnClickListener{
            moveToAddContact()
        }
        Log.d("contact2", contactList.toString())
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = RecyclerViewAdapter(contactList, LayoutInflater.from(this))
        recyclerView.adapter = adapter
        // 리사이클러 뷰에 레이아웃 매니저 장착
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // 리사이클러 뷰에 어답터 장착
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // 전달 받은 String 데이터를 출력
                val contact = Contact(
                    result.data?.getStringExtra("name") ?: "",
                    result.data?.getStringExtra("phoneNumber") ?: "",
                    result.data?.getStringExtra("mail") ?: "",
                    result.data?.getStringExtra("birthday") ?: "",
                    result.data?.getStringExtra("gender") ?: "",
                    result.data?.getStringExtra("memo") ?: "",
                )
                Log.d("contact2", contact.name)
                contactList.add(contact)
                setDescriptionTextVisibilityGone()
                adapter.notifyItemInserted(contactList.size)
            }
        }
    }

    fun moveToAddContact(){
        val intent = Intent(this@ListActivity, MainActivity::class.java)
        resultLauncher.launch(intent)
    }

    fun setDescriptionTextVisibilityGone(){
        descriptionText.visibility = View.GONE
    }
}

class RecyclerViewAdapter(
    //outer class
    var contactList : MutableList<Contact>,
    var inflater : LayoutInflater
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){ // 어떤 뷰홀더를 쓸건지 알려줘야함 ViewHolder는 클래스 안쪽에 만든다.(inner class)

    inner class ViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView){
        //RecyclerView의 뷰 홀더를 상속받아야만 개선된 ViewHolder를 사용할 수 있다.
        // 아이템뷰 컴포넌트를 구체적으로 찾는(홀드하는) 역할
        val userTitle : TextView
        val userName : TextView
        init{
            userTitle = itemView.findViewById(R.id.user_title)
            userName = itemView.findViewById(R.id.user_name)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 인플레이트 시켜서 뷰 홀더에게 넘겨준다.
        val view = inflater.inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //데이터를 아이템뷰의 뷰컴포넌트와 묶는다.(뷰를 채운다)
        holder.userTitle.text = contactList.get(position).name[0].toString()
        holder.userName.text = contactList.get(position).name
    }

    override fun getItemCount(): Int {
        // 전체 데이터의 크기 리턴
        return contactList.size
    }
}


class Contact(var name : String, var phoneNumber : String, var mail : String, var birthday : String, var gender : String, var memo : String){
    fun existsMail(): Boolean {
        return mail != ""
    }

    fun existsBirthday(): Boolean {
        return birthday != ""
    }

    fun existsGender(): Boolean {
        return gender != ""
    }

    fun existsMemo(): Boolean {
        return memo != ""
    }
}