package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contact)

        val name = intent.getStringExtra("name")
        val tel = intent.getStringExtra("tel")

        findViewById<TextView>(R.id.input_name).text = name
        findViewById<TextView>(R.id.input_tel).text = tel
    }
}