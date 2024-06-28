package campus.tech.kakao.contacts

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class AddInfo : AppCompatActivity() {
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