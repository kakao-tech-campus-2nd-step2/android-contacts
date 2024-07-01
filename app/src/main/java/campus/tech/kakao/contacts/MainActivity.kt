package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val contacts = mutableListOf<ContactDTO>()
    private lateinit var adapter: ContactAdapter


    /**
     * 다른 액티비티 (ContactAddActivity)를 시작하고 결과를 받아옴
     */
    private val addContactLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
        if (it.resultCode == RESULT_OK) {
            val contactName = it.data?.getStringExtra("contact_name")
            val contactPhone = it.data?.getStringExtra("contact_phone")
            val contactMail = it.data?.getStringExtra("contact_mail")
            val contactBirth = it.data?.getStringExtra("contact_birth")
            val contactGender = it.data?.getStringExtra("contact_gender")
            val contactMemo = it.data?.getStringExtra("contact_memo")
            if (contactName != null && contactPhone != null) {
                contacts.add(ContactDTO(contactName, contactPhone, contactMail, contactBirth, contactGender, contactMemo))
                adapter.notifyDataSetChanged()
            }
        }
    }

    /**
     * 리사이클러 뷰를 업데이트 하는 방법 notifyDataSetChanged()
     * 리스트의 크기와 아이템이 둘 다 변경되는 경우에 사용
     * 어느 상황에서나 사용 가능하다는 장점이 있으나, 퍼포먼스적인 측면에서 생각했을 때 별로  ..?
     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Intent 사용
        adapter = ContactAdapter(contacts) { contact ->
            val intent = Intent(this, ContactDetailActivity::class.java).apply {
                putExtra("contact_name", contact.name)
                putExtra("contact_phone", contact.phone)
                putExtra("contact_mail", contact.mail)
                putExtra("contact_birth", contact.birth)
                putExtra("contact_gender", contact.gender)
                putExtra("contact_memo", contact.memo)
            }
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter



        val actionBtn = findViewById<FloatingActionButton>(R.id.addButton)
        actionBtn.setOnClickListener {
            val intent = Intent(this, ContactAddActivity::class.java)
            addContactLauncher.launch(intent)
        }
    }
}
