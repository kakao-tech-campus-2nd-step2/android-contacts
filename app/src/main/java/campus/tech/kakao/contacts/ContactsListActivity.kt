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
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.model.Contact
import java.lang.Exception
import java.time.LocalDate

class ContactsListActivity : AppCompatActivity() {
    val contactList: MutableList<Contact> = mutableListOf()
    lateinit var recyclerView: RecyclerView
    lateinit var helpMessageText: TextView

    val activityLauncher: ActivityResultLauncher<Intent> =
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

        initiateRecyclerView()
        initiateAddContactButton()
        //testAddContacts()
        helpMessageText = findViewById(R.id.text_help)
    }

    fun testAddContacts() {
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
        addNewContact(Contact("박병호", "4442358", null, null, null, null))
    }

    fun initiateRecyclerView() {
        recyclerView = findViewById(R.id.contact_list_recyclerView)
        recyclerView.adapter =
            ContactListAdapter(LayoutInflater.from(this), this, contactList) { _, index ->
                clickContactItem(contactList[index])
            }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun initiateAddContactButton() {
        val addContactButton = findViewById<Button>(R.id.button_add_contact)
        addContactButton.setOnClickListener {
            startAddContactActivity()
        }
    }

    fun addNewContact(contact: Contact) {
        contactList.add(contact)
        recyclerView.adapter?.notifyItemInserted(contactList.size)

        setHelpMessageActive(false)
    }

    fun removeContact(contact: Contact) {
        contactList.remove(contact)

        if (contactList.size == 0) {
            setHelpMessageActive(true)
        }
    }

    fun startAddContactActivity() {
        val intent = Intent()
        val componentName: ComponentName = ComponentName(
            this@ContactsListActivity,
            AddContactActivity::class.java
        )
        intent.component = componentName
        activityLauncher.launch(intent)
    }

    fun startShowContactInfoActivity(contact: Contact) {
        val intent = Intent()
        val componentName = ComponentName(
            this@ContactsListActivity,
            ContactInfoActivity::class.java
        )
        intent.component = componentName
        intent.putExtra("contact", contact)
        startActivity(intent)
    }

    fun getContactFromIntent(intent: Intent?): Contact? {
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

    fun clickContactItem(contactItem: Contact) {
        Log.d(
            "KSC",
            "Name: ${contactItem.name}, Phone: ${contactItem.phoneNumber}\nBirthday: ${contactItem.birthday ?: "NULL"},Email: ${contactItem.email ?: "NULL"}"
        )
        startShowContactInfoActivity(contactItem)
    }

    fun setHelpMessageActive(active: Boolean) {
        helpMessageText.visibility = if (active) View.VISIBLE else View.GONE
    }
}