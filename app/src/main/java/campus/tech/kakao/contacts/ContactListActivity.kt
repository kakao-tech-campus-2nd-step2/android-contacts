package campus.tech.kakao.contacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactListActivity : Activity() {

    private val contacts = mutableListOf<Contact>()
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        contactAdapter = ContactAdapter(this, contacts)
        val recyclerViewContacts = findViewById<RecyclerView>(R.id.recycler_view_contacts)
        recyclerViewContacts.layoutManager = LinearLayoutManager(this)
        recyclerViewContacts.adapter = contactAdapter

        contactAdapter.itemClickListener = object : ContactAdapter.OnItemClickListener {
            override fun onItemClick(contact: Contact) {
                val intent = Intent(this@ContactListActivity, ContactDetailActivity::class.java).apply {
                    putExtra("name", contact.name)
                    putExtra("phone", contact.phone)
                    putExtra("email", contact.email)
                    putExtra("birthday", contact.birthday)
                    putExtra("genderId", contact.genderId)
                    putExtra("memo", contact.memo)
                }
                startActivity(intent)
            }
        }

        val fabAddContact = findViewById<FloatingActionButton>(R.id.fab_add_contact)
        fabAddContact.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivityForResult(intent, ADD_CONTACT_REQUEST)
        }

        checkEmptyState()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_CONTACT_REQUEST && resultCode == RESULT_OK) {
            data?.let {
                val name = it.getStringExtra("name") ?: return
                val phone = it.getStringExtra("phone") ?: return
                val email = it.getStringExtra("email") ?: ""
                val birthday = it.getStringExtra("birthday") ?: ""
                val genderId = it.getIntExtra("genderId", -1)
                val memo = it.getStringExtra("memo") ?: ""

                val newContact = Contact(name, phone, email, birthday, genderId, memo)
                contacts.add(newContact)
                contactAdapter.notifyItemInserted(contacts.size - 1)
                checkEmptyState()
            }
        }
    }

    private fun checkEmptyState() {
        val emptyText = findViewById<TextView>(R.id.empty_text)
        emptyText.visibility = if (contacts.isEmpty()) View.VISIBLE else View.GONE
    }

    companion object {
        private const val ADD_CONTACT_REQUEST = 1
    }
}
