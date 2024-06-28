package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class ContactListActivity : AppCompatActivity() {

    private val contacts = mutableListOf<Contact>()
    private lateinit var add: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        add = findViewById(R.id.addButton)
        recyclerView = findViewById(R.id.recyclerView)
        emptyText = findViewById(R.id.emptyText)

        //추가 버튼 클릭 시 동작
        add.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, 1)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ContactAdapter(contacts) { contact ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("CONTACT", contact)
            startActivity(intent)
        }

        //초기에 연락처가 없으면 emptyText를 보이도록 설정
        if (contacts.isEmpty()) {
            emptyText.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptyText.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val contact = data?.getParcelableExtra<Contact>("CONTACT")
            contact?.let {
                contacts.add(it)
                recyclerView.adapter?.notifyDataSetChanged()
                emptyText.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }
}
