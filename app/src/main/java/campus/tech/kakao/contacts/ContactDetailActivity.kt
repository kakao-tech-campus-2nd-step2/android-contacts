package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_detail)

        val contact = intent.getParcelableExtra<Contact>("contact") ?: return


        val nameTextView: TextView = findViewById(R.id.detailNameTextView)
        val phoneTextView: TextView = findViewById(R.id.detailPhoneTextView)
        val mailTextView: TextView = findViewById(R.id.detailMailTextView)
        val birthTextView: TextView = findViewById(R.id.detailBirthTextView)
        val genderTextView: TextView = findViewById(R.id.detailGenderTextView)
        val memoTextView: TextView = findViewById(R.id.detailMemoTextView)


        nameTextView.text = contact.name
        phoneTextView.text = contact.phoneNumber
        mailTextView.text = contact.mail
        birthTextView.text = contact.birth
        genderTextView.text = contact.gender
        memoTextView.text = contact.memo
    }
}
