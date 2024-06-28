package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WhoAmI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_whoam_i)

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contact = intent.getParcelableExtra<MainActivity.Contact>("contacts")
        contact?.let {
            val nameTextView = findViewById<TextView>(R.id.nameTextView)
            val emailTextView = findViewById<TextView>(R.id.emailTextView)
            val phoneTextView = findViewById<TextView>(R.id.phoneTextView)
            nameTextView.text = it.name
            emailTextView.text = it.email
            phoneTextView.text = it.phone
        }
    }
}
