package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ContactItemView.OnItemClickListener {
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var addButton: FloatingActionButton
    private lateinit var contactLayout: LinearLayout
    private lateinit var emptyNotice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerActivityResult()
        initViews()
    }

    private fun initViews() {
        contactLayout = findViewById(R.id.ll_contact)
        emptyNotice = findViewById(R.id.tv_empty_notice)
        addButton =
            findViewById<FloatingActionButton?>(R.id.fab_add).apply {
                setOnClickListener {
                    val intent = Intent(context, AddContactActivity::class.java)
                    activityResultLauncher.launch(intent)
                }
            }
    }

    private fun registerActivityResult() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.getStringExtra(CONTACT_DATA)?.let {
                        addContactItemView(contactJsonString = it)
                    }
                }
            }
    }

    private fun addContactItemView(contactJsonString: String) {
        emptyNotice.isVisible = false

        ContactItemView(this).apply {
            setContactData(contactJsonString)
            setOnItemClickListener(this@MainActivity)
            contactLayout.addView(this)
        }
    }

    override fun onItemClickListener(contactData: JSONObject) {
        Intent(this, ContactDetailActivity::class.java)
            .apply {
                putExtra(CONTACT_DATA, contactData.toString())
                startActivity(this)
            }
    }
}
