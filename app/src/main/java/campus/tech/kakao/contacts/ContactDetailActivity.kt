package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_detail)

        val nameTextView: TextView = findViewById(R.id.detailNameTextView)
        val phoneTextView: TextView = findViewById(R.id.detailPhoneTextView)
        val mailTextView: TextView = findViewById(R.id.detailMailTextView)
        val birthTextView: TextView = findViewById(R.id.detailBirthTextView)
        val genderTextView: TextView = findViewById(R.id.detailGenderTextView)
        val memoTextView: TextView = findViewById(R.id.detailMemoTextView)

        val name = intent.getStringExtra("name")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val mail = intent.getStringExtra("mail")
        val birth = intent.getStringExtra("birth")
        val gender = intent.getStringExtra("gender")
        val memo = intent.getStringExtra("memo")

        nameTextView.text = name
        phoneTextView.text = phoneNumber
        mailTextView.text = mail
        birthTextView.text = birth
        genderTextView.text = gender
        memoTextView.text = memo
    }
}
