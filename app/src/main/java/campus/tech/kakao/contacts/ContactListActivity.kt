package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.viewmodel.ContactViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactListActivity : AppCompatActivity() {
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        setupRecyclerView()
        setupAddContactButton()
        observeContacts()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@ContactListActivity)
    }

    private fun setupAddContactButton() {
        findViewById<FloatingActionButton>(R.id.AddContactButton).setOnClickListener {
            val intent = Intent(this@ContactListActivity, AddContactActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeContacts() {
        val inflater = LayoutInflater.from(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val emptyMessage = findViewById<TextView>(R.id.emptyMessage)

        contactViewModel.allContacts.observe(this) { contacts ->
            contacts?.let {
                if (it.isEmpty()) {
                    recyclerView.visibility = RecyclerView.GONE
                    emptyMessage.visibility = TextView.VISIBLE
                } else {
                    recyclerView.visibility = RecyclerView.VISIBLE
                    emptyMessage.visibility = TextView.GONE
                    val adapter = ContactAdapter(it, inflater)
                    recyclerView.adapter = adapter
                }
            }
        }
    }

}
