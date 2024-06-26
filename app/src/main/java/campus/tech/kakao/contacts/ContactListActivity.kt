package campus.tech.kakao.contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.viewmodel.ContactViewModel

class ContactListActivity : AppCompatActivity() {
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        val inflater = LayoutInflater.from(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        contactViewModel.allContacts.observe(this) { contacts ->
            contacts?.let {
                val adapter = ContactAdapter(it, inflater)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this@ContactListActivity)
                it.forEach { contact ->
                    Log.d("testt", "(Contact Data) name : ${contact.name} phone : ${contact.phone}")
                }
            }
        }
    }
}
