package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class contactsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_list)
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            Intent(this, MainActivity::class.java).let{
                startActivity(it)
            }
        }
    }
}