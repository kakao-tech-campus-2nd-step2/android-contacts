package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/**
 * 연락처 상세 목록
 * 띄우기만 하면 됨
 */

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val nameTextView = findViewById<TextView>(R.id.detail_name)
        val phoneTextView = findViewById<TextView>(R.id.detail_phone)
        val mailTextView = findViewById<TextView>(R.id.detail_mail)
        val birthTextView = findViewById<TextView>(R.id.detail_birth)
        val genderTextView = findViewById<TextView>(R.id.detail_gender)
        val memoTextView = findViewById<TextView>(R.id.detail_memo)

        // intent 사용 데이터 교환
        val intent = intent
        nameTextView.text = intent.getStringExtra("contact_name")
        phoneTextView.text = intent.getStringExtra("contact_phone")
        mailTextView.text = intent.getStringExtra("contact_mail")
        birthTextView.text = intent.getStringExtra("contact_birth")
        genderTextView.text = intent.getStringExtra("contact_gender")
        memoTextView.text = intent.getStringExtra("contact_memo")

    }
}