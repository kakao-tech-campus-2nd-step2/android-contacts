package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val contacts = mutableListOf<Contact>()
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactGuide: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactGuide = findViewById(R.id.contactGuide)
        val contactAddBtn: FloatingActionButton = findViewById(R.id.contactAddBtn)
        recyclerView = findViewById(R.id.recyclerView)
        contactAdapter = ContactAdapter(contacts) { contact ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = contactAdapter

        val startActivityLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                when (it.resultCode) {
                    RESULT_OK -> {
                        val contact = it.data?.getParcelableExtra("contact") as? Contact
                        contact?.let { newContact ->
                            contacts.add(newContact)
                            contactAdapter.notifyItemInserted(contacts.size - 1)
                            update()
                        }
                    }
                }
            }
        contactAddBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, ContactAddActivity::class.java)
            startActivityLauncher.launch(intent)
        }


    }

    private fun update() {
        if (contacts.isEmpty()) {
            recyclerView.visibility = View.GONE
            contactGuide.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            contactGuide.visibility = View.GONE
        }
    }}
