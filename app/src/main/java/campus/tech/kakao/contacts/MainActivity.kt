package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val contactList: MutableList<ContactInfo> = mutableListOf()
    private lateinit var addInforLauncher: ActivityResultLauncher<Intent>
    private lateinit var listView: ListView
    private lateinit var adapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        adapter = ContactAdapter(this, contactList)
        listView.adapter = adapter

        addInforLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data
                    receivedInfor(data)
                    adapter.notifyDataSetChanged()
                }
            }
        val addInfor = findViewById<Button>(R.id.imageButton)
        addInfor.setOnClickListener {
            val toAddInfor = Intent(this@MainActivity, AddInforActivity::class.java)
            addInforLauncher.launch(toAddInfor)
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = adapter.getItem(position)
            val tosp = Intent(this@MainActivity, SpecificInformationActivity::class.java).apply {
                putExtra("name", selectedItem?.name ?: "")
                putExtra("phoneNumber", selectedItem?.phoneNumber ?: "")
                putExtra("email", selectedItem?.email ?: "")
                putExtra("birth", selectedItem?.birth ?: "")
                putExtra("memo", selectedItem?.memo ?: "")
                putExtra("gender", selectedItem?.gender ?: "")
            }
            startActivity(tosp)
        }
    }
    private fun receivedInfor(intent: Intent?) {
        intent?.let {
            val addName = it.getStringExtra("inforname") ?: "Null"
            val addPhoneNumber = it.getStringExtra("infornumber") ?: "null"
            val addEmail = it.getStringExtra("inforEmail") ?: ""
            val addBirth = it.getStringExtra("inforBirth") ?: ""
            val addMemo = it.getStringExtra("inforMemo") ?: ""
            val addGender = it.getStringExtra("inforGender") ?: ""

            val contactInfo =
                ContactInfo(addName, addPhoneNumber, addEmail, addBirth, addMemo, addGender)
            contactList.add(contactInfo)
        }
    }
}
class ContactAdapter(context: Context, private val data: List<ContactInfo>) :
    ArrayAdapter<ContactInfo>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.contact_items, parent, false)

        val contact = getItem(position)
        val textViewName = itemView.findViewById<TextView>(R.id.textViewId)
        val textViewName2 = itemView.findViewById<TextView>(R.id.textViewName)
        val contactName = contact?.name
        textViewName.text = contactName?.firstOrNull()?.toString() ?: ""
        textViewName2.text = contact?.name

        return itemView
    }
}
data class ContactInfo(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val birth: String,
    val memo: String,
    val gender: String
)