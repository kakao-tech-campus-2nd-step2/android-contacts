package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactOwnerInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_owner_info)

        val intent = intent
        val name = intent.extras?.getString("name")!!
        val phoneNumber = intent.extras?.getString("phoneNumber")!!

        val nameView = findViewById<TextView>(R.id.name)
        nameView.text = name

        val phoneNumberView = findViewById<TextView>(R.id.phoneNumber)
        phoneNumberView.text = phoneNumber
    }
}