package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.database.Contact
import campus.tech.kakao.contacts.viewmodel.ContactViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactListActivity : AppCompatActivity() {
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        setupRecyclerView()
        setupAddContactButton()
        observeContacts()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@ContactListActivity)
    }

    private fun setupAddContactButton() {
        findViewById<FloatingActionButton>(R.id.AddContactButton).setOnClickListener {
            startActivity(Intent(this@ContactListActivity, MainActivity::class.java))
        }
    }

    private fun observeContacts() {
        val inflater = LayoutInflater.from(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        contactViewModel.allContacts.observe(this) { contacts ->
            contacts?.let {
                val adapter = ContactAdapter(it, inflater)
                recyclerView.adapter = adapter
                logContacts(it)
            }
        }
    }

    private fun logContacts(contacts: List<Contact>) {
        contacts.forEach { contact ->
            Log.d("testt", "(Contact Data) name : ${contact.name} phone : ${contact.phone}")
        }
    }
}
