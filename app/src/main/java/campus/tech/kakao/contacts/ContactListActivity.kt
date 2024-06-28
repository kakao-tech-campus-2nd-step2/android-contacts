package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactListActivity : AppCompatActivity() {
    val contactList = contactRepository.contactList
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
            val intent = Intent(this,ContactAddActivity::class.java)
            addContactLauncher.launch(intent)
        }

        contactRecyclerView.adapter = RecyclerAdapter(this,contactList,LayoutInflater.from(this))
        contactRecyclerView.layoutManager = LinearLayoutManager(this)


    }

}

class RecyclerAdapter(
    val context : Context,
    var contactList : List<Contact>,
    var inflater : LayoutInflater,

    ) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    class ViewHolder(context: Context,itemView : View) : RecyclerView.ViewHolder(itemView){
        val contactName : TextView
        val contactImage : TextView
        init{
            contactImage = itemView.findViewById<TextView>(R.id.contactImage)
            contactName = itemView.findViewById<TextView>(R.id.contactName)

            itemView.setOnClickListener {
                val intent = Intent(context,ContactDetailActivity::class.java)
                intent.putExtra("position",adapterPosition)
                context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.contact_element,parent,false)

        return RecyclerAdapter.ViewHolder(context,view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.contactName.text = contact.name
        holder.contactImage.text = contact.name[0].toString()
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}