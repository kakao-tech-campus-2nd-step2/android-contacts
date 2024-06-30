package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class whoamI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whoam_i)

        val contact = intent.getSerializableExtra("contact") as? Contact
        if (contact != null) {
            findViewById<TextView>(R.id.nameTextView).text = contact.name
            findViewById<TextView>(R.id.phoneTextView).text = contact.phone
            findViewById<TextView>(R.id.emailTextView).text = contact.email

        }
    }
}
