package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var descriptionText: TextView
    lateinit var plusButton: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var contactList: MutableList<Contact>
    lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initVar()
        initListener()

    }

    fun initVar(){
        contactList = mutableListOf<Contact>()
        adapter = RecyclerViewAdapter(contactList, LayoutInflater.from(this), this)
        plusButton = findViewById<TextView>(R.id.plus_item_button)
        descriptionText = findViewById<TextView>(R.id.description_text)
        initRecyclerView()
        initResultLauncher()
    }

    fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun initResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val contact = Contact(
                        result.data?.getStringExtra("name") ?: "",
                        result.data?.getStringExtra("phoneNumber") ?: "",
                        result.data?.getStringExtra("mail") ?: "",
                        result.data?.getStringExtra("birthday") ?: "",
                        result.data?.getStringExtra("gender") ?: "",
                        result.data?.getStringExtra("memo") ?: "",
                    )
                    Log.d("contact2", contact.name)
                    contactList.add(contact)
                    setDescriptionTextVisibilityGone()
                    adapter.notifyItemInserted(contactList.size)
                }
            }
    }
    fun initListener(){
        plusButton.setOnClickListener {
            moveToAddContact()
        }
    }
    fun moveToAddContact() {
        val intent = Intent(this@ListActivity, MainActivity::class.java)
        resultLauncher.launch(intent)
    }

    fun setDescriptionTextVisibilityGone() {
        descriptionText.visibility = View.GONE
    }

}

class RecyclerViewAdapter(
    var contactList: MutableList<Contact>,
    var inflater: LayoutInflater,
    var context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userTitle: TextView
        val userName: TextView

        init {
            userTitle = itemView.findViewById(R.id.user_title)
            userName = itemView.findViewById(R.id.user_name)
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val intent = Intent(context, DetailActivity::class.java)
                val sendContact = contactList.get(position)
                intent.putExtra("name", sendContact.name)
                intent.putExtra("phoneNumber", sendContact.phoneNumber)
                intent.putExtra("mail", sendContact.mail)
                intent.putExtra("birthday", sendContact.birthday)
                intent.putExtra("gender", sendContact.gender)
                intent.putExtra("memo", sendContact.memo)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userTitle.text = contactList.get(position).name[0].toString()
        holder.userName.text = contactList.get(position).name
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}

class Contact(
    var name: String,
    var phoneNumber: String,
    var mail: String,
    var birthday: String,
    var gender: String,
    var memo: String
) {}