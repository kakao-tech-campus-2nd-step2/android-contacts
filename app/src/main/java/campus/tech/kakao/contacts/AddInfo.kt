package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class AddInfo : AppCompatActivity() {
    private lateinit var addBtn: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var infoText: TextView
    private var contactList = ArrayList<Contact>()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)

        initialize()
        setUpListeners()

    }

    private fun initialize() {
        addBtn = findViewById(R.id.addBtn)
        recyclerView = findViewById(R.id.recyclerView)
        infoText = findViewById(R.id.infoText)

    }

    private fun setUpListeners() {
        addBtn.setOnClickListener {
            val intent = Intent(this@AddInfo, MainActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

}

