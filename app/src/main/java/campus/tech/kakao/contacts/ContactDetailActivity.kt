package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val nameTextView = findViewById<TextView>(R.id.name)
        val phoneTextView = findViewById<TextView>(R.id.phone)
        val mailTextView = findViewById<TextView>(R.id.mail)
        val birthdayTextView = findViewById<TextView>(R.id.birthday)
        val genderTextView = findViewById<TextView>(R.id.gender)
        val memoTextView = findViewById<TextView>(R.id.memo)

        nameTextView.text = intent.getStringExtra("name")
        phoneTextView.text = intent.getStringExtra("phone")
        mailTextView.text = intent.getStringExtra("mail")
        birthdayTextView.text = intent.getStringExtra("birthday")
        genderTextView.text =intent.getStringExtra("gender")
        memoTextView.text = intent.getStringExtra("memo")
    }
}