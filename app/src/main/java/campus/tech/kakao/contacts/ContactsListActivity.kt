package campus.tech.kakao.contacts

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.model.Contact
import java.lang.Exception
import java.time.LocalDate

class ContactsListActivity : AppCompatActivity() {
    val contactList: MutableList<Contact> = mutableListOf()
    lateinit var recyclerView: RecyclerView
    val activityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK) {
            val contact = getContactFromResult(it)
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
    }

    fun initiateRecyclerView() {
        recyclerView = findViewById(R.id.contact_list_recyclerView)
        recyclerView.adapter = ContactListAdapter(LayoutInflater.from(this), this, contactList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun initiateAddContactButton(){
        val addContactButton = findViewById<Button>(R.id.button_add_contact)
        addContactButton.setOnClickListener {
            startAddContactActivity()
        }
    }

    fun addNewContact(contact: Contact) {
        contactList.add(contact)
        recyclerView.adapter?.notifyItemInserted(contactList.size)
    }

    fun removeContact(contact: Contact) {
        contactList.remove(contact)
    }

    fun startAddContactActivity(){
        val intent = Intent()
        val componentName: ComponentName = ComponentName(
            this@ContactsListActivity,
            AddContactActivity::class.java
        )
        intent.component = componentName
        activityLauncher.launch(intent)

    }

    fun birthdayFromString(str: String?): LocalDate?{
        if(str == null){
            return null
        }
        return try{
            LocalDate.parse(str)
        } catch(e: Exception){
            null
        }
    }

    fun getContactFromResult(result: ActivityResult): Contact?{
        val extras = result.data?.extras ?: return null
        val name = extras.getString(AddContactActivity.KEY_NAME)
        val phoneNumber = extras.getString(AddContactActivity.KEY_PHONE)
        if(name == null || phoneNumber == null)
            return null

        return Contact(name, phoneNumber, extras.getString(AddContactActivity.KEY_EMAIL),
            extras.getString(AddContactActivity.KEY_GENDER)?.toIntOrNull(),
            birthdayFromString(extras.getString(AddContactActivity.KEY_BIRTHDAY)),
            extras.getString(AddContactActivity.KEY_MEMO))
    }
}