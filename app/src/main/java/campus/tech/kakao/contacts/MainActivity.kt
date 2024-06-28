package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var contactGuide: TextView
    private lateinit var contactListView: ListView
    private val contacts = mutableListOf<Contact>()
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactGuide = findViewById(R.id.contactGuide)
        contactListView = findViewById(R.id.contactListView)
        val contactAddBtn: FloatingActionButton = findViewById(R.id.contactAddBtn)

        contactAdapter = ContactAdapter(this, contacts)
        contactListView.adapter = contactAdapter

        val startActivityLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK && it.data != null) {
                    val data = it.data!!
                    val contact = Contact(
                        data.getStringExtra("name") ?: "",
                        data.getStringExtra("phoneNumber") ?: "",
                        data.getStringExtra("mail") ?: "",
                        data.getStringExtra("birth") ?: "",
                        data.getStringExtra("gender") ?: "",
                        data.getStringExtra("memo") ?: ""
                    )
                    contacts.add(contact)
                    contactAdapter.notifyDataSetChanged()
                    contactGuide.visibility = if (contacts.isEmpty()) View.VISIBLE else View.GONE
                    contactListView.visibility = if (contacts.isEmpty()) View.GONE else View.VISIBLE
                }
            }

        contactAddBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, ContactAdd::class.java)
            startActivityLauncher.launch(intent)
        }

        contactListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val contact = contacts[position]
            val intent = Intent(this@MainActivity, ContactDetail::class.java).apply {
                putExtra("name", contact.name)
                putExtra("phoneNumber", contact.phoneNumber)
                putExtra("mail", contact.mail)
                putExtra("birth", contact.birth)
                putExtra("gender", contact.gender)
                putExtra("memo", contact.memo)
            }
            startActivity(intent)
        }
    }
}
