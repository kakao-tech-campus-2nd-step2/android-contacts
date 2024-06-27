package campus.tech.kakao.contacts

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.Settings.Global
import android.view.View
import android.view.ViewGroup
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

class collection : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var tvmessage: TextView
    private lateinit var recyclerView : RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        //데이터 저장 및 로드
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_collection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvmessage = findViewById(R.id.message)
        //데이터 베이스 생성
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "PhoneCollection"
        ).build()
        //RecyclerView 설정
        recyclerView = findViewById<TextView>(R.id.recycler_View)
        recyclerView.layoutManager = LinearLayoutManager(this)
        contactAdapter = ContactAdapter()
        recyclerView.adapter = contactAdapter
        //db에서 연락처 목록 가져옴
        GlobalScope.launch {
            val contacts = db.contactDao().getAllContacts()
            withContext(Dispatchers.Main) {
                contactAdapter.setData(contacts)
            }
        }
        fun showMessage() {
            GlobalScope.launch {
                if (db.userDao().getUsers().isEmpty()) {
                    withContext(Dispatchers.Main) {
                        tvmessage.visibility = View.VISIBLE
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        tvmessage.visibility = View.GONE
                    }
                }
            }
        }
    }
    class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder> () {
        private var contacts : List<Contact> = emptyList()

        fun setData(newContacts : List<Contact>) {
            contacts = newContacts
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ContactAdapter.ViewHolder {
            TODO("Not yet implemented")
        }
    }
}



