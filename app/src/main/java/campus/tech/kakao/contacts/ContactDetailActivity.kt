package campus.tech.kakao.contacts

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ContactDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val nameTextView = findViewById<TextView>(R.id.contact_name_content)
        val phoneTextView = findViewById<TextView>(R.id.contact_phone_content)
        val emailTextView = findViewById<TextView>(R.id.contact_mail_content)
        val birthdayTextView = findViewById<TextView>(R.id.contact_birthday_content)
        val genderTextView = findViewById<TextView>(R.id.contact_gender_content)
        val memoTextView = findViewById<TextView>(R.id.contact_memo_content)

        val nameSection = findViewById<View>(R.id.name_section)
        val phoneSection = findViewById<View>(R.id.phone_section)
        val emailSection = findViewById<View>(R.id.email_section)
        val birthdaySection = findViewById<View>(R.id.birthday_section)
        val genderSection = findViewById<View>(R.id.gender_section)
        val memoSection = findViewById<View>(R.id.memo_section)

        val intent = intent
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val birthday = intent.getStringExtra("birthday")
        val genderId = intent.getIntExtra("genderId", -1)
        val memo = intent.getStringExtra("memo")

        if (!name.isNullOrEmpty()) {
            nameSection.visibility = View.VISIBLE
            nameTextView.text = name
        }

        if (!phone.isNullOrEmpty()) {
            phoneSection.visibility = View.VISIBLE
            phoneTextView.text = phone
        }

        if (!email.isNullOrEmpty()) {
            emailSection.visibility = View.VISIBLE
            emailTextView.text = email
        }

        if (!birthday.isNullOrEmpty()) {
            birthdaySection.visibility = View.VISIBLE
            birthdayTextView.text = birthday
        }

        if (genderId != -1) {
            genderSection.visibility = View.VISIBLE
            val genderText = if (genderId == R.id.gender_male) getString(R.string.gender_male) else getString(R.string.gender_female)
            genderTextView.text = genderText
        }

        if (!memo.isNullOrEmpty()) {
            memoSection.visibility = View.VISIBLE
            memoTextView.text = memo
        }
    }
}
