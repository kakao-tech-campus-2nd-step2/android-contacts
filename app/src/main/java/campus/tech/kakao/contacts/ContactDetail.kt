package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val mail = intent.getStringExtra("email")
        val birthday = intent.getStringExtra("birthday")
        val gender = intent.getStringExtra("gender")
        val memo = intent.getStringExtra("memo")

        findViewById<TextView>(R.id.detailname).text = name
        findViewById<TextView>(R.id.detailphone).text = phone
        findViewById<TextView>(R.id.detailmail).text = mail
        findViewById<TextView>(R.id.detailbirth).text = birthday
        findViewById<TextView>(R.id.detailgender).text = gender
        findViewById<TextView>(R.id.detailmemo).text = memo

    }
}