package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CollectionActivity : AppCompatActivity() {
    private lateinit var messageTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private val addButton: Button by lazy { findViewById(R.id.addbutton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_collection)
        setupWindowInsets()
        messageTextView = findViewById(R.id.message)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        loadContacts()
        showMessage()

        addButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showMessage() {
        messageTextView.visibility = if (getContacts().isEmpty()) View.VISIBLE else View.GONE
    }

    private fun loadContacts() {
        val contacts = getContacts()
        contactAdapter = ContactAdapter(contacts)
        recyclerView.adapter = contactAdapter
    }

    private fun getContacts(): List<Contact> {
        val sharedPreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE)
        val contacts = mutableListOf<Contact>()

        contacts.add(Contact("John Doe", "123-456-7890", "Male", "john.doe@example.com", "Hello, world!", "1990-01-01"))
        contacts.add(Contact("Jane Smith", "987-654-3210", "Female", "jane.smith@example.com", "Hi there!", "1985-05-15"))
        contacts.add(Contact("Bob Johnson", "555-555-5555", "Male", "bob.johnson@example.com", "Howdy!", "1975-12-31"))
        return contacts
    }


    private fun enableEdgeToEdge() {
        // Implement edge-to-edge functionality
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}