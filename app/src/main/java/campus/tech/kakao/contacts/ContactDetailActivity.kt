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

        val contact = intent.getSerializableExtra("contactDetail") as Contact

        val nameTextView: TextView = findViewById<TextView>(R.id.contact_name)
        val phoneNumberTextView: TextView = findViewById(R.id.contact_phone_number)

        nameTextView.text = contact.name
        phoneNumberTextView.text = contact.phoneNumber
    }
}