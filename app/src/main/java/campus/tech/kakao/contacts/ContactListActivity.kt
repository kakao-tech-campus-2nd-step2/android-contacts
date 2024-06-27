package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactListActivity : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val REQUEST_CODE_ADD_USER = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userList = mutableListOf<UserData>()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerViewAdapter=RecyclerViewAdapter(userList)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val btnAdd = findViewById<ImageView>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_USER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_USER && resultCode == RESULT_OK) {
            val userData = data?.getSerializableExtra("userData") as? UserData
            if (userData != null) {
                recyclerViewAdapter.addUserData(userData)
            }
        }
    }

}


class RecyclerViewAdapter(
    val userList: MutableList<UserData>,
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userName: TextView
        val userFirstName: TextView
        init {
            userName = itemView.findViewById(R.id.userName)
            userFirstName = itemView.findViewById(R.id.userFirstName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.text = user.name
        holder.userFirstName.text = user.name[0].uppercase()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun addUserData(userData: UserData) {
        userList.add(userData)
        notifyItemInserted(userList.size - 1)
    }
}