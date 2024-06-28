package campus.tech.kakao.contacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity() {

    private lateinit var contactLinearView: LinearLayout
    private val contacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)

        contactLinearView = findViewById<LinearLayout>(R.id.contactLinearView)
        val contactAddButton = findViewById<Button>(R.id.contactAddButton)


        contactAddButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == Activity.RESULT_OK) {
            val name = data?.getStringExtra("name") ?: ""
            val phone = data?.getStringExtra("phone") ?: ""
            val email = data?.getStringExtra("email") ?: ""
            val birthday = data?.getStringExtra("birthday") ?: ""
            val gender = data?.getStringExtra("gender") ?: ""
            val memo = data?.getStringExtra("memo") ?: ""
            val contact = Contact(name, phone, email, birthday, gender, memo)
            contacts.add(contact)
            addContactView(contact)
        }
    }

    private fun addContactView(contact: Contact) {
        val inflater = LayoutInflater.from(this)
        val contactView = inflater.inflate(R.layout.item_layout, contactLinearView, false)

        val tvInitial = contactView.findViewById<TextView>(R.id.itemInitial)
        val tvName = contactView.findViewById<TextView>(R.id.itemName)

        tvInitial.text = contact.name.first().toString()
        tvName.text = contact.name

        contactLinearView.addView(contactView)
    }

    companion object {
        const val REQUEST_CODE_ADD_CONTACT = 1
    }
}