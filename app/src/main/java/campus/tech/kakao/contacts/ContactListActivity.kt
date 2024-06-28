package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactListActivity : AppCompatActivity() {
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var adapter: ContactAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val addButton: ExtendedFloatingActionButton = findViewById(R.id.fab)

        // 리사이클러뷰 설정
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ContactAdapter(emptyList())
        recyclerView.adapter = adapter

        // 플로팅 액션 버튼 클릭 리스너 설정
        addButton.setOnClickListener {
            Log.d("ContactListActivity", "Launching ContactAddActivity")
            val intent: Intent = Intent(this, ContactAddActivity::class.java)
            startActivity(intent)
        }

        // LiveData 관찰
        contactViewModel.contacts.observe(this) { contacts ->
            Log.d("testt", contacts.toString())
            Log.d("ContactListActivity", "Contacts observed: $contacts")
            adapter.updateContacts(contacts)
        }


        }
    }


class ContactAdapter(private var contacts: List<Contact>): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val contactName : TextView = view.findViewById(R.id.contactName)

        init {
            view.setOnClickListener {
                // TODO : 클릭 시 연락처 세부 정보 창 이동하기
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.contactName.text = contact.name
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
    fun updateContacts(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }

}