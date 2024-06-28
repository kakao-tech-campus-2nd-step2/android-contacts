package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

val contactList = mutableListOf<Contact>()

class ContactMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_main)

        val text = findViewById<TextView>(R.id.text)
        val floatingBtn = findViewById<FloatingActionButton>(R.id.floatingBtn)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val mail = intent.getStringExtra("mail") ?: ""
        val birthday = intent.getStringExtra("birthday") ?: ""
        val gender = intent.getStringExtra("gender") ?: ""
        val memo = intent.getStringExtra("memo") ?: ""

        if (name != null && phone != null) {
            contactList.add(Contact(name, phone, mail, birthday, gender, memo))
        }

        if (contactList.size > 0) {
            text.visibility = View.INVISIBLE
        } else {
            text.visibility = View.VISIBLE
        }

        floatingBtn.setOnClickListener {
            val intent = Intent(this, ContactWritingActivity::class.java)
            //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }

        val adapter = RecyclerViewAdapter(contactList, LayoutInflater.from(this))

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.setListener(object: RecyclerViewAdapter.ItemClickListener{
            override fun onClick(v: View, position: Int) {
                val intent = Intent(this@ContactMainActivity, ContactDetailActivity::class.java)
                intent.putExtra("name", contactList.get(position).name)
                intent.putExtra("phone", contactList.get(position).phone)
                intent.putExtra("mail", contactList.get(position).mail)
                intent.putExtra("birthday", contactList.get(position).birthday)
                intent.putExtra("gender", contactList.get(position).gender)
                intent.putExtra("memo", contactList.get(position).memo)
                startActivity(intent)
            }
        })
    }
}

class RecyclerViewAdapter(
    val contactList: MutableList<Contact>, val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val profile: TextView

        init {
            name = itemView.findViewById<TextView>(R.id.name)
            profile = itemView.findViewById<TextView>(R.id.profile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = contactList.get(position).name
        holder.profile.text = contactList.get(position).name.substring(0, 1)

        holder.itemView.setOnClickListener {
            recyclerListener.onClick(it, position)
        }
    }
    interface ItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setListener(itemClickListener: ItemClickListener) {
        this.recyclerListener = itemClickListener
    }

    lateinit var recyclerListener : ItemClickListener

    override fun getItemCount(): Int {
        return contactList.size
    }
}

class Contact(val name: String, val phone: String, val mail: String, val birthday: String, val gender: String, val memo: String)