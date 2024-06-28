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

        // View 초기화
        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val phoneNumTextView: TextView = findViewById(R.id.phoneNumTextView)
        val emailTextView: TextView = findViewById(R.id.emailTextView)
        val birthdayTextView: TextView = findViewById(R.id.birthdayTextView)
        val genderTextView: TextView = findViewById(R.id.genderTextView)
        val memoTextView: TextView = findViewById(R.id.memoTextView)

        // 인텐트로부터 연락처 정보 수신
        val name = intent.getStringExtra("name")
        val phoneNum = intent.getStringExtra("phoneNum")
        val email = intent.getStringExtra("email")
        val birthday = intent.getStringExtra("birthday")
        val gender = intent.getStringExtra("gender")
        val memo = intent.getStringExtra("memo")

        // View에 연락처 정보 설정
        nameTextView.text = name
        phoneNumTextView.text = phoneNum
        emailTextView.text = email
        birthdayTextView.text = birthday
        genderTextView.text = gender
        memoTextView.text = memo

        }
    }