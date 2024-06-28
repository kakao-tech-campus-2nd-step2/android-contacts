package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactListActivity : AppCompatActivity() {

    lateinit var plusbtn: Button
    lateinit var infotext : TextView
    val personList = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUI()
        goToAddContact()
        displayContact()
    }

    private fun setUI(){
        plusbtn = findViewById(R.id.btn_plus)
        infotext = findViewById(R.id.text_info)
    }

    fun goToAddContact() {
        plusbtn.setOnClickListener {
            val intent = Intent(this, SaveContactActivity::class.java)
            startActivity(intent)
        }
    }

    fun displayContact(){
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")

        if (name != null && phone != null) {
            infotext.visibility = View.GONE

            personList.add(Person(name.toString(), phone))
            with(findViewById<RecyclerView>(R.id.contactRecyclerView)) {
                this.layoutManager = LinearLayoutManager(this@ContactListActivity)
                this.adapter = PersonAdapter(
                    personList = personList,
                    inflater = LayoutInflater.from(this@ContactListActivity)
                )
            }
        }
    }
}
class PersonAdapter(
    val personList: MutableList<Person>,
    val inflater: LayoutInflater
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val text_name: TextView
        val text_initial: TextView
        init{
            text_name = itemView.findViewById(R.id.name)
            text_initial = itemView.findViewById(R.id.nameinitial)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.activity_person_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val person = personList.get(position)
        val viewHolder = holder as ViewHolder
        viewHolder.text_name.text = person.name
        viewHolder.text_initial.text = person.name[0].toString()
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}
