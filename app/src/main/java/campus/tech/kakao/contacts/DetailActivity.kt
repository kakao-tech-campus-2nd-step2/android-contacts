package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {

    val contactManager = ContactManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val nameText = intent.getStringExtra("nameText") ?: ""
        val phoneNumberText = intent.getStringExtra("phoneNumberText") ?: ""
        val emailText = intent.getStringExtra("emailText") ?: ""
        val birthDayText = intent.getStringExtra("birthDayText") ?: ""
        val genderText = intent.getStringExtra("genderText") ?: ""
        val memoText = intent.getStringExtra("memoText") ?: ""

        val emailLayout = findViewById<LinearLayoutCompat>(R.id.emailLayout)
        val birthDayLayout = findViewById<LinearLayoutCompat>(R.id.birthDayLayout)
        val genderLayout = findViewById<LinearLayoutCompat>(R.id.genderLayout)
        val memoLayout = findViewById<LinearLayoutCompat>(R.id.memoLayout)

        val name = findViewById<TextView>(R.id.name)
        contactManager.setTextViewText(name, nameText)
        val phoneNumber = findViewById<TextView>(R.id.phoneNumber)
        contactManager.setTextViewText(phoneNumber,phoneNumberText)
        val email = findViewById<TextView>(R.id.email)
        contactManager.setTextViewText(email, emailText, emailLayout)
        val birthDay = findViewById<TextView>(R.id.birthDay)
        contactManager.setTextViewText(birthDay, birthDayText, birthDayLayout)
        val gender = findViewById<TextView>(R.id.gender)
        contactManager.setTextViewText(gender,genderText,genderLayout)
        val memo = findViewById<TextView>(R.id.memo)
        contactManager.setTextViewText(memo,memoText,memoLayout)


    }
}