package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.model.Contact

class ContactsListActivity : AppCompatActivity() {
    val contactList: MutableList<Contact> = mutableListOf()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_list)
    }

    fun initiateRecyclerView() {
        recyclerView = findViewById(R.id.contact_list_recyclerView)
        recyclerView.adapter = ContactListAdapter(LayoutInflater.from(this), this, contactList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun addNewContact(contact: Contact) {
        contactList.add(contact)
    }

    fun removeContact(contact: Contact) {
        contactList.remove(contact)
    }
}