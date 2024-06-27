package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_main)

        val floatingBtn = findViewById<FloatingActionButton>(R.id.floatingBtn)

        floatingBtn.setOnClickListener{
            val intent = Intent(this, ContactWritingActivity::class.java)
            startActivity(intent)
        }
    }
}