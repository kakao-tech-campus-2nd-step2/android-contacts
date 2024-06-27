package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val contacts = mutableListOf<MutableList<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gotoAddContactBtn = findViewById<Button>(R.id.goto_add_contact)

        gotoAddContactBtn.setOnClickListener {
            gotoAddContact()
        }
    }

    override fun onStart() {
        super.onStart()
        getContactList()
    }

    fun getContactList() {
        val contactList = findViewById<RecyclerView>(R.id.contact_list)
        contactList.adapter = contactListAdapter(contacts, LayoutInflater.from(this))
        contactList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun gotoAddContact() {
        val intent = Intent(this@MainActivity, AddContact::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1 -> {
                when (resultCode) {
                    RESULT_OK -> {
                        val name = data?.extras?.getString("name")
                        val phoneNumber = data?.extras?.getString("phoneNumber")
                        contacts.add(mutableListOf(name!!, phoneNumber!!))
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}

class contactListAdapter(
    val contacts: MutableList<MutableList<String>>,
    val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<contactListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView

        init {
            nameView = itemView.findViewById(R.id.contact_owner_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.contact_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView.text = contacts.get(position)[0]
    }

}