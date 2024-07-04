package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.Contact
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyMessage: TextView
    private lateinit var fab: FloatingActionButton
    private val contacts = mutableListOf<Contact>() // 연락처 목록

    private val addContactLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val name = result.data?.getStringExtra("name")
            val phone = result.data?.getStringExtra("phone")
            if (name != null && phone != null) {
                val contact = Contact(name, phone)
                contacts.add(contact)
                recyclerView.adapter?.notifyDataSetChanged()
                checkIfEmpty()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        recyclerView = findViewById(R.id.recyclerView)
        emptyMessage = findViewById(R.id.emptyMessage)
        fab = findViewById(R.id.fab)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ContactAdapter(contacts)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            addContactLauncher.launch(intent)
        }

        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        if (contacts.isEmpty()) {
            emptyMessage.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptyMessage.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }
}
