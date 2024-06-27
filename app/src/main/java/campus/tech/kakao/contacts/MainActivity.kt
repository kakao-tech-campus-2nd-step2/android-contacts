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
        val activityResultLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                when (it.resultCode) {
                    RESULT_OK -> {
                        Log.d("Main","Success")
                        val resContact =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                                it.data?.extras?.getSerializable("res",Contact::class.java)
                            else
                                it.data?.extras?.getSerializable("res") as Contact?
                        resContact?.let { contactList.add(resContact) }
                        contactAdapter.notifyDataSetChanged()
                        if (contactList.size > 0)
                            findViewById<TextView>(R.id.init_message).visibility = TextView.GONE
                        Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
                    }
                    RESULT_CANCELED -> {
                        Log.d("Main","Canceled")
                        Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        val addButton: FloatingActionButton = findViewById(R.id.add_contact)
        val contactRecyclerView: RecyclerView = findViewById(R.id.contact_recycler_view)

        addButton.setOnClickListener {
            val contactIntent: Intent = Intent(this, ContactActivity::class.java)
            activityResultLauncher.launch(contactIntent)
        }

        contactRecyclerView.adapter = contactAdapter
        contactRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
    fun test() {}
}

class ContactRecyclerAdapter(
    var contactList: MutableList<Contact>,
    val inflater: LayoutInflater,
    val mainContext: Context
) : RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder>() {
    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemLayout: ConstraintLayout
        val profile: TextView
        val name: TextView
        init {
            itemLayout = itemView.findViewById<ConstraintLayout>(R.id.item_contact).apply {
                setOnClickListener {
                    val contactIntent: Intent = Intent(mainContext, ContactInfoActivity::class.java)
                    contactIntent.putExtra("info", contactList[adapterPosition])
                    mainContext.startActivity(contactIntent)
                }
            }
            profile = itemView.findViewById(R.id.contact_item_profile)
            name = itemView.findViewById(R.id.contact_item_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = inflater.inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.profile.text = contactList[position].name[0].toString()
        holder.name.text = contactList[position].name
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}