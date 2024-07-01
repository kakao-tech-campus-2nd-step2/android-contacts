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
    private lateinit var nameValueText: TextView
    private lateinit var phoneValueText: TextView
    private lateinit var emailValueText: TextView
    private lateinit var birthdayValueText: TextView
    private lateinit var genderValueText: TextView
    private lateinit var memoValueText: TextView

    private lateinit var birthdayText: TextView
    private lateinit var nameText: TextView
    private lateinit var phoneText: TextView
    private lateinit var emailText: TextView
    private lateinit var genderText: TextView
    private lateinit var memoText: TextView
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
        nameText = findViewById(R.id.label_text_name)
        phoneText = findViewById(R.id.label_text_phone)
        emailText = findViewById(R.id.label_text_email)
        birthdayText = findViewById(R.id.label_text_birthday)
        genderText = findViewById(R.id.label_text_gender)
        memoText = findViewById(R.id.label_text_memo)

        nameValueText = findViewById(R.id.text_name)
        phoneValueText = findViewById(R.id.text_phone)
        emailValueText = findViewById(R.id.text_email)
        birthdayValueText = findViewById(R.id.text_birthday)
        genderValueText = findViewById(R.id.text_gender)
        memoValueText = findViewById(R.id.text_memo)
    }

    private fun updateLayoutVisibility(label: View, valueView: TextView, text: String?) {
        if (text == null) {
            valueView.visibility = View.GONE
            label.visibility = View.GONE
        } else {
            valueView.text = text
            valueView.visibility = View.VISIBLE
            label.visibility = View.VISIBLE
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
        updateLayoutVisibility(nameText, nameValueText, contact.name)
        updateLayoutVisibility(phoneText, phoneValueText, contact.phoneNumber)
        updateLayoutVisibility(emailText, emailValueText, contact.email)
        updateLayoutVisibility(birthdayText, birthdayValueText, contact.birthday?.toString())
        updateLayoutVisibility(genderText, genderValueText, parseGender(contact.gender))
        updateLayoutVisibility(memoText, memoValueText, contact.memo)
    }
}