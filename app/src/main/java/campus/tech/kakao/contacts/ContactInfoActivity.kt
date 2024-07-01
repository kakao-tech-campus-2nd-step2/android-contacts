package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.contacts.model.Contact

class ContactInfoActivity : AppCompatActivity() {
    private lateinit var nameText: TextView
    private lateinit var phoneText: TextView
    private lateinit var emailText: TextView
    private lateinit var birthdayText: TextView
    private lateinit var genderText: TextView
    private lateinit var memoText: TextView

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
            intent.getSerializableExtra(AddContactActivity.KEY_CONTACT) as? Contact
        }
    }

    private fun initiateLayoutValues() {
        nameLayout = findViewById(R.id.name_layout)
        phoneLayout = findViewById(R.id.phone_layout)
        emailLayout = findViewById(R.id.email_layout)
        birthdayLayout = findViewById(R.id.birthday_layout)
        genderLayout = findViewById(R.id.gender_layout)
        memoLayout = findViewById(R.id.memo_layout)

        nameText = findViewById(R.id.text_name)
        phoneText = findViewById(R.id.text_phone)
        emailText = findViewById(R.id.text_email)
        birthdayText = findViewById(R.id.text_birthday)
        genderText = findViewById(R.id.text_gender)
        memoText = findViewById(R.id.text_memo)
    }

    private fun updateLayoutVisibility(layout: ViewGroup, value: String?) {
        if (value == null) {
            layout.visibility = View.GONE
        } else {
            layout.visibility = View.VISIBLE
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
        nameText.text = contact.name
        phoneText.text = contact.phoneNumber
        emailText.text = contact.email
        birthdayText.text = contact.birthday?.toString()
        genderText.text = parseGender(contact.gender)
        memoText.text = contact.memo

        updateLayoutVisibility(emailLayout, contact.email)
        updateLayoutVisibility(birthdayLayout, contact.birthday?.toString())
        updateLayoutVisibility(genderLayout, parseGender(contact.gender))
        updateLayoutVisibility(memoLayout, contact.memo)
    }
}