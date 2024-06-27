package campus.tech.kakao.contacts

import android.app.Application
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

class ContactAdapter<Contact>(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter<Any?>.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.contact_name)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
    }
    override fun getItemCount(): Int {
        return contacts.size
    }
}
class CollectionActivity : AppCompatActivity() {
    private lateinit var db: MainActivity.AppDatabase
    private lateinit var tvmessage: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter<Any?>
    private val container: FrameLayout by lazy { findViewById(R.id.container) }
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
        db = (application as MyApplication).db

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

    private fun showMessage() {
        GlobalScope.launch {
            val hasUsers = db.userDao().getUsers().isNotEmpty()
            withContext(Dispatchers.Main) {
                tvmessage.visibility = if (hasUsers) View.GONE else View.VISIBLE
            }
        }
    }
}

class MyApplication : Application() {
    lateinit var db: MainActivity.AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            MainActivity.AppDatabase::class.java, "PhoneCollection"
        ).build()
    }
}




