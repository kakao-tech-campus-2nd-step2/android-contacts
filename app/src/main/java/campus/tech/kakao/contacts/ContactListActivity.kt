package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class ContactListActivity : AppCompatActivity() {
    private lateinit var contactList: MutableList<Contact>
    private lateinit var adapter: ContactRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        contactList = mutableListOf<Contact>()
        contactList.add(Contact("이지은", "1","1","1","1","1"))
        contactList.add(Contact("1", "1","1","1","1","1"))
        contactList.add(Contact("1", "1","1","1","1","1"))
        contactList.add(Contact("1", "1","1","1","1","1"))
        contactList.add(Contact("1", "1","1","1","1","1"))

        val contactRecyclerView: RecyclerView = findViewById<RecyclerView>(R.id.contact_list_recyclerview)

        adapter = ContactRecyclerAdapter(
            contactList = contactList,
            inflater = LayoutInflater.from(this@ContactListActivity)
        )
        contactRecyclerView.layoutManager = LinearLayoutManager(this@ContactListActivity)
        contactRecyclerView.adapter = adapter

        val addButton: ImageView = findViewById<ImageView>(R.id.add_button)
        addButton.setOnClickListener{
            val intent: Intent = Intent(this@ContactListActivity, ContactAddActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            1 -> {
                when(resultCode){
                    AppCompatActivity.RESULT_OK -> {
                        val contact: Contact? = data?.getSerializableExtra("contactInfo") as Contact?
                        contact?.let {
                            contactList.add(it)
                            adapter.notifyItemInserted(contactList.size-1)
                        }
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}

class Contact(
    val name: String,
    val phoneNumber: String,
    val email: String?,
    birthDate: String?,
    gender: String?,
    memo: String?
) : Serializable

class ContactRecyclerAdapter(
    val contactList: MutableList<Contact>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameInitialTextView: TextView
        val nameTextView: TextView
        init {
            nameInitialTextView = itemView.findViewById(R.id.name_initial_text)
            nameTextView = itemView.findViewById(R.id.name_text)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_contact, parent, false))
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contact = contactList.get(position)
        (holder as ViewHolder).nameInitialTextView.text = contact.name.substring(0 until 1)
        (holder as ViewHolder).nameTextView.text = contact.name
    }

}


