package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactListActivity : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var userList: MutableList<UserData>
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

        initUserList()
        initRecyclerView()
        initAddButton()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val helpMessage: TextView = findViewById<TextView>(R.id.helpMessage)

        if (requestCode == REQUEST_CODE_ADD_USER && resultCode == RESULT_OK) {
            val userData = data?.getSerializableExtra("userData") as? UserData
            if (userData != null) {
                recyclerViewAdapter.addUserData(userData)

            }
        }
        helpMessage.visibility = if (userList.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun setOnApplyWindowInsetsListener(){
    }

    private fun initUserList(){
        Log.d("ContactListActivity", "inituserlist")
        userList = mutableListOf()
    }

    private fun initRecyclerView(){
        Log.d("ContactListActivity", "initrecyclerview")
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerViewAdapter=RecyclerViewAdapter(userList)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initAddButton(){
        Log.d("ContactListActivity", "initaddbtn")
        setAddButtonListener()
    }

    private fun setAddButtonListener(){
        Log.d("ContactListActivity", "setaddbuttonlistener")
        val btnAdd = findViewById<ImageView>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            Log.d("ContactListActivity", "Add button clicked")
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_USER)
            Log.d("ContactListActivity", "startActivityForResult called")
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

            itemView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val intent = Intent(itemView.context, UserInfoActivity::class.java)
                    intent.putExtra("userData", userList[position])
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
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