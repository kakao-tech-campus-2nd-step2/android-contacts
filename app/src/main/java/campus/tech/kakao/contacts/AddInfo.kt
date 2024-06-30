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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        }

        override fun getItemCount(): Int {
            return contactList.size
        }
    }
}

