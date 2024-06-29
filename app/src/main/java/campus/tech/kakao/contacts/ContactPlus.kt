package campus.tech.kakao.contacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactPlus : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var emptyView : TextView
    private lateinit var fab : FloatingActionButton
    private lateinit var contactsAdapter : ContactsAdapter
    private val contactsList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_plus)

        recyclerView = findViewById(R.id.recycler)
        emptyView = findViewById(R.id.empty)
        fab = findViewById(R.id.fab)

        contactsAdapter = ContactsAdapter(contactsList) { contact ->
            val intent = Intent(this, ContactDetail::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = contactsAdapter

        fab.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT)
        }

        updateUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Contact>("contact")?.let { newContact ->
                contactsList.add(newContact)
                contactsAdapter.notifyItemInserted(contactsList.size - 1)
                updateUI()
            }
        }
    }

    private fun updateUI() {
        if (contactsList.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }

    companion object {
        const val REQUEST_CODE_ADD_CONTACT = 1
    }
}
