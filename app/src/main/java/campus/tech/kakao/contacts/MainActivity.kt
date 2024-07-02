package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var contactAdapter: ContactAdapter
    private val contactList = ArrayList<Contact>()
    private val contactManager = ContactManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        contactAdapter = ContactAdapter(contactList, LayoutInflater.from(this))
        val contactView = findViewById<RecyclerView>(R.id.contactRecyclerView)
        contactView.adapter = contactAdapter
        contactView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val emptyView = findViewById<TextView>(R.id.emptyMainView)
        contactManager.viewText(emptyView, contactAdapter.itemCount)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val name = result.data?.getStringExtra(Constants.EXTRA_NAME_TEXT) ?: ""
                val phoneNumber = result.data?.getStringExtra(Constants.EXTRA_PHONE_NUMBER_TEXT) ?: ""
                val email = result.data?.getStringExtra(Constants.EXTRA_EMAIL_TEXT) ?: ""
                val birthDay = result.data?.getStringExtra(Constants.EXTRA_BIRTH_DAY_TEXT) ?: ""
                val gender = result.data?.getStringExtra(Constants.EXTRA_GENDER_TEXT) ?: ""
                val memo = result.data?.getStringExtra(Constants.EXTRA_MEMO_TEXT) ?: ""

                val contact = Contact(name, phoneNumber, email, birthDay, gender, memo)
                contactList.add(contact)
                contactAdapter.notifyDataSetChanged()
                contactManager.viewText(emptyView, contactAdapter.itemCount)
            }
        }

        val add = findViewById<FloatingActionButton>(R.id.floatingAddButton)
        add.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            launcher.launch(intent)
        }

        contactAdapter.itemClickListener = object : ContactAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val item = contactList[position]
                val intent = Intent(this@MainActivity, DetailActivity::class.java)

                intent.putExtra(Constants.EXTRA_NAME_TEXT, item.name)
                intent.putExtra(Constants.EXTRA_EMAIL_TEXT, item.email)
                intent.putExtra(Constants.EXTRA_PHONE_NUMBER_TEXT, item.phoneNumber)
                intent.putExtra(Constants.EXTRA_BIRTH_DAY_TEXT, item.birthDay)
                intent.putExtra(Constants.EXTRA_GENDER_TEXT, item.gender)
                intent.putExtra(Constants.EXTRA_MEMO_TEXT, item.memo)

                startActivity(intent)
            }
        }
    }
}
