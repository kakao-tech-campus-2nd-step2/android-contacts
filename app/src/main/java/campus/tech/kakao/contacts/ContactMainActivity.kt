package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")

        floatingBtn.setOnClickListener{
            val intent = Intent(this, ContactWritingActivity::class.java)
            startActivity(intent)
        }

        Log.d("uin", "main " + name + " " + phone)

    }
}