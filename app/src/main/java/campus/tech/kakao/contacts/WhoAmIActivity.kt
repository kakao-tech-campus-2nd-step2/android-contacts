package campus.tech.kakao.contacts

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class WhoAmIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_who_am_iactivity)

        val contact = intent.getSerializableExtra("contact") as? Contact
        if (contact != null) {
            findViewById<TextView>(R.id.nameTextView).text = contact.name
            findViewById<TextView>(R.id.phoneTextView).text = contact.phone
            findViewById<TextView>(R.id.emailTextView).text = contact.email
            findViewById<TextView>(R.id.BirTextView).text = contact.birthday
            findViewById<TextView>(R.id.memoTextView).text = contact.message
            findViewById<TextView>(R.id.genderTextView).text = contact.gender
        }
    }

    private fun getContacts(): List<Contact> {
        val sharedPreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE)
        val contacts = mutableListOf<Contact>()

        contacts.add(
            Contact(
                "John Doe",
                "123-456-7890",
                "Male",
                "john.doe@example.com",
                "Hello, world!",
                "1990-01-01"
            )
        )
        contacts.add(
            Contact(
                "Jane Smith",
                "987-654-3210",
                "Female",
                "jane.smith@example.com",
                "Hi there!",
                "1985-05-15"
            )
        )
        contacts.add(
            Contact(
                "Bob Johnson",
                "555-555-5555",
                "Male",
                "bob.johnson@example.com",
                "Howdy!",
                "1975-12-31"
            )
        )
        return contacts
    }
}

