package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("name") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val birthdate = intent.getStringExtra("birthdate") ?: ""
        val gender = intent.getStringExtra("gender") ?: ""
        val notes = intent.getStringExtra("notes") ?: ""

        if (name.isNotEmpty()) {
            findViewById<TextView>(R.id.name_label).text = "이름"
            findViewById<TextView>(R.id.name_value).text = name
        }
        if (phone.isNotEmpty()) {
            findViewById<TextView>(R.id.phone_label).text = "전화번호"
            findViewById<TextView>(R.id.phone_value).text = phone
        }
        if (email.isNotEmpty()) {
            findViewById<TextView>(R.id.email_label).text = "메일"
            findViewById<TextView>(R.id.email_value).text = email
        }
        if (birthdate.isNotEmpty()) {
            findViewById<TextView>(R.id.birthdate_label).text = "생일"
            findViewById<TextView>(R.id.birthdate_value).text = birthdate
        }
        if (gender.isNotEmpty()) {
            findViewById<TextView>(R.id.gender_label).text = "성별"
            findViewById<TextView>(R.id.gender_value).text = gender
        }
        if (notes.isNotEmpty()) {
            findViewById<TextView>(R.id.notes_label).text = "메모"
            findViewById<TextView>(R.id.notes_value).text = notes
        }
    }
}
