package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactList = mutableListOf<Contact>()
        val contactAdapter = ContactRecyclerAdapter(contactList, layoutInflater, this)
        val addContactLauncher: ActivityResultLauncher<Intent> = createAddContactLauncher(contactList, contactAdapter)

        val addButton: FloatingActionButton = findViewById(R.id.add_contact)
        val contactRecyclerView: RecyclerView = findViewById(R.id.contact_recycler_view)

        contactRecyclerView.adapter = contactAdapter
        contactRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        addButton.setOnClickListener {
            launchAddContact(addContactLauncher)
        }
    }

    fun createAddContactLauncher(contactList: MutableList<Contact>, contactAdapter: ContactRecyclerAdapter): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    val resContact = getResultContact(result)
                    updateLayout(resContact, contactList, contactAdapter)
                }
                RESULT_CANCELED -> {
                    Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getResultContact(result: ActivityResult): Contact? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            result.data?.extras?.getSerializable(Contact.KEY ,Contact::class.java)
        else
            result.data?.extras?.getSerializable(Contact.KEY) as Contact?
    }

    private fun updateLayout(contact: Contact?, contactList: MutableList<Contact>, contactAdapter: ContactRecyclerAdapter) {
        if (contact is Contact) {
            updateList(contact, contactList)
            updateAdapter(contactAdapter, contactList.size - 1)
            Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(this, "Contact is Null", Toast.LENGTH_SHORT).show()
        if (contactList.size > 0)
            findViewById<TextView>(R.id.init_message).visibility = TextView.GONE
    }

    private fun updateList(contact: Contact, contactList: MutableList<Contact>) {
        contactList.add(contact)
    }

    private fun updateAdapter(contactAdapter: ContactRecyclerAdapter, pos: Int) {
        contactAdapter.notifyItemInserted(pos)
    }

    fun launchAddContact(addContactLauncher: ActivityResultLauncher<Intent>) {
        val contactIntent: Intent = Intent(this, ContactActivity::class.java)
        addContactLauncher.launch(contactIntent)
    }
}