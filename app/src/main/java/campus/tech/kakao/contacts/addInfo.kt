package campus.tech.kakao.contacts

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class addInfo : AppCompatActivity() {
    private lateinit var addBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)

        initialize()
        setUpListeners()

    }

    private fun initialize() {
        addBtn = findViewById(R.id.addBtn)
    }

    private fun setUpListeners() {
        addBtn.setOnClickListener {
            val intent: Intent = Intent()
            val componentName: ComponentName = ComponentName(
                "campus.tech.kakao.contacts",
                "campus.tech.kakao.contacts.MainActivity"
            )
            intent.component = componentName
            startActivity(intent)
        }
    }


}