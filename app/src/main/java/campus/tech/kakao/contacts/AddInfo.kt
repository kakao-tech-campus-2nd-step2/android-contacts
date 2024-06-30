package campus.tech.kakao.contacts

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class AddInfo : AppCompatActivity() {
    private lateinit var addBtn: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var infoText: TextView
    private var contactList = ArrayList<Contact>()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)

        initialize()
        setUpListeners()
        setRecyclerView()
        setupResultLauncher()

    }

    private fun initialize() {
        addBtn = findViewById(R.id.addBtn)
        recyclerView = findViewById(R.id.recyclerView)
        infoText = findViewById(R.id.infoText)

    }

    private fun setUpListeners() {
        addBtn.setOnClickListener {
            val intent = Intent(this@AddInfo, MainActivity::class.java)
            resultLauncher.launch(intent)
        }
    }


    private fun setRecyclerView() {
        recyclerView.adapter = RecyclerViewAdapter(contactList, LayoutInflater.from(this),this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupResultLauncher() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val contact = data?.getParcelableExtra<Contact>("CONTACT_RESULT")
                contact?.let {
                    contactList.add(it)
                    recyclerView.adapter?.notifyItemInserted(contactList.size - 1)
                    Log.d("AddInfo", "New contact added: ${it.name}")
                    isShowText()
                }
            }
        }
    }

    private fun isShowText() {
        if (contactList.isEmpty()) {
            infoText.visibility = View.VISIBLE
        }
        else {
            infoText.visibility = View.GONE
        }
    }


    class RecyclerViewAdapter(
        var contactList: ArrayList<Contact>,
        var inflater: LayoutInflater,
        private val context: Context
    ): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val faceImg: ImageView
            val name_text_view: TextView

            init {
                faceImg = itemView.findViewById(R.id.faceImg)
                name_text_view = itemView.findViewById(R.id.name_text_view)
            }

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.contact_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val contact = contactList[position]
            holder.name_text_view.text = contact.name

            holder.itemView.setOnClickListener {
                val intent = Intent(context, ContactDetail::class.java).apply {
                    putExtra("CONTACT", contact)
                }
                context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return contactList.size
        }
    }
}

