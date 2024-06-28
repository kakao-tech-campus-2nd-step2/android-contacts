package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import campus.tech.kakao.contacts.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private val contactViewModel: ContactViewModel by viewModels()
    private lateinit var adapter: ListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        val plusButton: ImageButton = findViewById(R.id.PlusButton)

        initListView()

        plusButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        handleIncomingIntent()
    }

    private fun initListView() {
        adapter = ListViewAdapter()
        binding.listView.adapter = adapter

        contactViewModel.contactListLiveData.observe(this) { contactList ->
            adapter.notifyDataSetChanged()
        }
    }

    private fun handleIncomingIntent() {
        val name = intent.getStringExtra("name") ?: ""
        val phoneNumber = intent.getStringExtra("phoneNumber") ?: ""

        if (name.isNotEmpty() && phoneNumber.isNotEmpty()) {
            Log.d("ListActivity", "New contact: $name, $phoneNumber")
            contactViewModel.addContact(contact(name, name[0].toString(), phoneNumber))
            // Intent 데이터를 초기화하여 중복 추가 방지
            intent.removeExtra("name")
            intent.removeExtra("phoneNumber")
        }
    }
}
