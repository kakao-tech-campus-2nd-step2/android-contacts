package campus.tech.kakao.contacts

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.Settings.Global
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class ContactAdapter<Contact>(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.namelist)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_person, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.toString()
    }
    override fun getItemCount(): Int {
        return contacts.size
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

        // MainActivity에서 생성한 AppDatabase 인스턴스 가져오기
        db = (application as MainActivity).db

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
                contactAdapter = ContactAdapter(this@CollectionActivity, contacts)
                recyclerView.adapter = contactAdapter
            }
        }
    }
}

class ContactAdapter(private val context: Context, private val contacts: List<Any?>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_whoam_i, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, whoamI::class.java)
            intent.putExtra("contact", contact)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = contacts.size
}

class whoamI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whoam_i)
        val contact = intent.getSerializableExtra("contact") as? Any

    }
}

private fun Intent.putExtra(s: String, contact: Any?) {

}











