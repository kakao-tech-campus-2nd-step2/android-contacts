package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import campus.tech.kakao.contacts.model.Contact

class ContactInfoActivity : AppCompatActivity() {
    private lateinit var birthdayLayout: ViewGroup
    private lateinit var nameLayout: ViewGroup
    private lateinit var phoneLayout: ViewGroup
    private lateinit var emailLayout: ViewGroup
    private lateinit var genderLayout: ViewGroup
    private lateinit var memoLayout: ViewGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)

        initiateLayoutValues()
        val contact = getContactFromIntent(intent)
        if (contact != null) {
            updateValuesWithContact(contact)
        }
    }

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

    private fun initiateLayoutValues() {
        nameLayout = findViewById(R.id.name_layout)
        phoneLayout = findViewById(R.id.phone_layout)
        emailLayout = findViewById(R.id.email_layout)
        birthdayLayout = findViewById(R.id.birthday_layout)
        genderLayout = findViewById(R.id.gender_layout)
        memoLayout = findViewById(R.id.memo_layout)
    }

    private fun updateLayout(layout: ViewGroup, value: String?) {
        if (value == null) {
            layout.visibility = View.GONE
        } else {
            (layout[CONTACT_LAYOUT_VALUE_INDEX] as TextView?)?.text = value
        }
    }

    private fun parseGender(gender: Int?): String? {
        return when (gender) {
            null -> null
            Contact.GENDER_FEMALE -> "여성"
            Contact.GENDER_MALE -> "남성"
            else -> "?"
        }
    }

    private fun updateValuesWithContact(contact: Contact) {
        (nameLayout[CONTACT_LAYOUT_VALUE_INDEX] as TextView?)?.text = contact.name
        (phoneLayout[CONTACT_LAYOUT_VALUE_INDEX] as TextView?)?.text = contact.phoneNumber

        updateLayout(emailLayout, contact.email)
        updateLayout(birthdayLayout, contact.birthday?.toString())
        updateLayout(genderLayout, parseGender(contact.gender))
        updateLayout(memoLayout, contact.memo)
    }

    companion object {
        private const val CONTACT_LAYOUT_VALUE_INDEX = 1
    }
}