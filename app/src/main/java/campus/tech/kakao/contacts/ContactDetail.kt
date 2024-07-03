package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contact = intent.getParcelableExtra<Contact>("contact") ?: return

        val profileImageView: ImageView = findViewById(R.id.profile_image_view)
        val nameTextView: TextView = findViewById(R.id.name_text_view)
        val phoneTextView: TextView = findViewById(R.id.phone_text_view)
        val mailTextView: TextView = findViewById(R.id.mail)
        val birthdayTextView: TextView = findViewById(R.id.birthday)
        val genderTextView: TextView = findViewById(R.id.gender)
        val memoTextView: TextView = findViewById(R.id.memo)

        profileImageView.setImageResource(contact.profile)
        nameTextView.text = contact.name
        phoneTextView.text = contact.phoneNumber
        mailTextView.text = contact.email?.ifEmpty { "" } ?: ""
        birthdayTextView.text = contact.birthday?.ifEmpty { "" } ?: ""
        genderTextView.text = contact.gender?.ifEmpty { "" } ?: ""
        memoTextView.text = contact.memo?.ifEmpty { "" } ?: ""
    }
}
