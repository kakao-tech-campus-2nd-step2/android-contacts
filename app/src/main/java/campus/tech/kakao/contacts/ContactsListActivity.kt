package campus.tech.kakao.contacts

import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.model.Contact
import java.time.LocalDate

class ContactsListActivity : AppCompatActivity() {
    private val contactList: MutableList<Contact> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var helpMessageText: TextView

    private val activityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val contact = getContactFromIntent(it.data)
                if (contact != null) {
                    addNewContact(contact)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_list)

        helpMessageText = findViewById(R.id.text_help)
        initiateRecyclerView()
        initiateAddContactButton()
        testAddContacts()
    }

    private fun testAddContacts() {
        addNewContact(
            Contact(
                "권성찬",
                "010123123",
                "kscksc@ksc.com",
                AddContactActivity.GENDER_MALE,
                LocalDate.now(),
                "Hello!!"
            )
        )
        addNewContact(Contact("장진욱", "4442358", null, null, null, null))
    }

    private fun initiateRecyclerView() {
        recyclerView = findViewById(R.id.contact_list_recyclerView)
        recyclerView.adapter =
            ContactListAdapter(LayoutInflater.from(this), this, contactList) { _, index ->
                clickContactItem(contactList[index])
            }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initiateAddContactButton() {
        val addContactButton = findViewById<Button>(R.id.button_add_contact)
        addContactButton.setOnClickListener {
            startAddContactActivity()
        }
    }

    private fun addNewContact(contact: Contact) {
        contactList.add(contact)
        recyclerView.adapter?.notifyItemInserted(contactList.size)

        setHelpMessageActive(false)
    }

    private fun removeContact(contact: Contact) {
        contactList.remove(contact)

        if (contactList.size == 0) {
            setHelpMessageActive(true)
        }
    }

    private fun startAddContactActivity() {
        val intent = Intent()
        val componentName = ComponentName(
            this@ContactsListActivity,
            AddContactActivity::class.java
        )
        intent.component = componentName
        activityLauncher.launch(intent)
    }

    private fun startShowContactInfoActivity(contact: Contact) {
        val intent = Intent()
        val componentName = ComponentName(
            this@ContactsListActivity,
            ContactInfoActivity::class.java
        )
        intent.component = componentName
        intent.putExtra("contact", contact)
        startActivity(intent)
    }

    @Suppress("DEPRECATION")
    private fun getContactFromIntent(intent: Intent?): Contact? {
        if (intent == null)
            return null
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                AddContactActivity.KEY_CONTACT,
                Contact::class.java
            ) as Contact
        } else {
            intent.getSerializableExtra(AddContactActivity.KEY_CONTACT) as Contact?
        }
    }

    private fun clickContactItem(contactItem: Contact) {
        Log.d(
            "KSC",
            "Name: ${contactItem.name}, Phone: ${contactItem.phoneNumber}\nBirthday: ${contactItem.birthday ?: "NULL"},Email: ${contactItem.email ?: "NULL"}"
        )
        startShowContactInfoActivity(contactItem)
    }

    private fun setHelpMessageActive(active: Boolean) {
        helpMessageText.visibility = if (active) View.VISIBLE else View.GONE
    }
}