package campus.tech.kakao.contacts

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.databinding.ActivitySubBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.Flow

class SubActivity : AppCompatActivity(), ContactsAdapter.OnItemClickListener {
    private lateinit var addFab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var viewModel: SubViewModel
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        addFab = findViewById(R.id.floatingActionButton)
        recyclerView = findViewById(R.id.recyclerView)
        textView = findViewById(R.id.textView)

        val intent = Intent(this, MainActivity::class.java)
        addFab.setOnClickListener {
            startActivity(intent)
        }

//        var ar = arrayOf("zz", "44", "","","","")
//        Contacts.addContact(ar)
//        var arr = arrayOf("하하", "74", "","","","")
//        Contacts.addContact(arr)

        contactsAdapter = ContactsAdapter(Contacts.getList(), this)
        contactsAdapter.notifyDataSetChanged()

        recyclerView.adapter = contactsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        viewModel = ViewModelProvider(this).get(SubViewModel::class.java)
//        viewModel.data.observe(this, Observer { data ->
//            contactsAdapter.updateItems(data)
//        })
//
//        viewModel.updateData(Contacts.getList())
    }

    override fun onResume() {
        super.onResume()
        contactsAdapter.notifyDataSetChanged()

        if (Contacts.getSize() < 1)
            textView.visibility = View.VISIBLE
        else
            textView.visibility = View.GONE

    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("contIdx", position)
        startActivity(intent)
    }
}