package campus.tech.kakao.contacts.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.R
import campus.tech.kakao.contacts.View.Adapter.RecyclerAdapter
import campus.tech.kakao.contacts.Repository.ContactRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactListActivity : AppCompatActivity() {
    val contactList = ContactRepository.contactList
    lateinit var contactListEmptyText : TextView
    lateinit var contactRecyclerView : RecyclerView

    val addContactLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        when(it.resultCode){
            RESULT_OK -> {
                updateRecyclerView()
                handleEmptyText()
            }
        }
    }

    fun handleEmptyText(){
        if(contactListEmptyText.visibility == VISIBLE) contactListEmptyText.visibility = GONE
    }

    fun updateRecyclerView(){
        contactRecyclerView.adapter?.notifyItemInserted(contactList.size-1)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)
        val addContactBtn = findViewById<FloatingActionButton>(R.id.addContactBtn)
        contactRecyclerView = findViewById<RecyclerView>(R.id.contactList)
        contactListEmptyText = findViewById<TextView>(R.id.contactListEmptyText)

        addContactBtn.setOnClickListener {
            val intent = Intent(this, ContactAddActivity::class.java)
            addContactLauncher.launch(intent)
        }

        contactRecyclerView.adapter = RecyclerAdapter(this,contactList,LayoutInflater.from(this))
        contactRecyclerView.layoutManager = LinearLayoutManager(this)


    }

}