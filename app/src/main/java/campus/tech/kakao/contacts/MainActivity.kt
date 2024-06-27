package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactAdapter = ContactAdapter(contactList, LayoutInflater.from(this))
        val contactView = findViewById<RecyclerView>(R.id.contactRecyclerView)
        contactView.adapter = contactAdapter
        contactView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                Log.d("result", "넘어왔어요옹")
                val name = result.data?.getStringExtra("nameText") ?: ""
                val phoneNumber = result.data?.getStringExtra("phoneNumberText") ?: ""
                val email = result.data?.getStringExtra("emailText") ?: ""
                val birthDay = result.data?.getStringExtra("birthDayText") ?: ""
                val gender = result.data?.getStringExtra("genderText") ?: ""
                val memo = result.data?.getStringExtra("memoText") ?: ""

                val contact = Contact(name, phoneNumber, email, birthDay, gender, memo)
                contactList.add(contact)
                contactAdapter.notifyItemInserted(contactList.size - 1) // Notify only the new item
            }
        }

        val add = findViewById<FloatingActionButton>(R.id.floatingAddButton)
        add.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            launcher.launch(intent)
        }
    }
}
