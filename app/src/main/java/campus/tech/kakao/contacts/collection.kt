package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val contactData: ContactData
) {
    data class ContactData(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String,
        val phone: String,
        val gender: String,
        val email: String,
        val message: String,
        val birthday: String
    )
}

class ContactAdapter(private val contacts: List<MainActivity.Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val phoneTextView: TextView = itemView.findViewById(R.id.phone)

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, WhoamiActivity::class.java).also {
                    it.putExtra("contact", contacts[adapterPosition].name)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
        holder.phoneTextView.text = contact.phone
    }

    override fun getItemCount() = contacts.size
}

private fun Intent.putExtra(s: String, contactData: Contact.ContactData) {

}

class WhoamiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whoam_i)
    }
}

class CollectionActivity : AppCompatActivity() {
    private lateinit var db: MainActivity.AppDatabase
    private lateinit var tvmessage: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private val addButton: Button by lazy { findViewById(R.id.addbutton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_collection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvmessage = findViewById(R.id.message)

        db = (application as MainActivity).db

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
        GlobalScope.launch {
            val hasUsers = db.userDao().getAllUsers().isEmpty()
            withContext(Dispatchers.Main) {
                tvmessage.visibility = if (hasUsers) View.GONE else View.VISIBLE
            }
        }
    }

    private fun loadContacts() {
        GlobalScope.launch {
            val contacts = db.contactDao().getAllContacts()
            withContext(Dispatchers.Main) {
                contactAdapter = ContactAdapter(contacts)
                recyclerView.adapter = contactAdapter
            }
        }
    }
}














