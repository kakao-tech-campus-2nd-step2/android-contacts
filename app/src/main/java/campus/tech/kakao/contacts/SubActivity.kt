package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SubActivity : AppCompatActivity() {
    private lateinit var addFab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        addFab = findViewById(R.id.floatingActionButton)

        val intent = Intent(this, MainActivity::class.java)
        addFab.setOnClickListener {
            startActivity(intent)
        }
    }
}