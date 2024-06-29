package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.databinding.ActivityContactListBinding

class ContactListActivity : AppCompatActivity() {
    val personList = mutableListOf<Person>()
    lateinit var binding: ActivityContactListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        goToAddContact()
    }

    override fun onRestart(){
        super.onRestart()
        getData()
        for (p in personList) {
            Log.d("ContactListActivity", "Person: ${p.name}, ${p.phone}, ${p.email}, ${p.birth}, ${p.gender}, ${p.note}")
        }
        displayContact()
    }

    override fun onDestroy() {
        super.onDestroy()
        personList.clear()
    }

    fun goToAddContact() {
        binding.btnPlus.setOnClickListener {
            val intent = Intent(this, SaveContactActivity::class.java)
            startActivity(intent)
        }
    }

    fun displayContact(){
        Log.d("ContactListActivity","${personList.size}")
        if (personList.size != 0) {
            binding.textInfo.visibility = View.GONE

            with(binding.contactRecyclerView) {
                layoutManager = LinearLayoutManager(this@ContactListActivity)
                adapter = PersonAdapter(
                    personList = personList,
                    inflater = LayoutInflater.from(this@ContactListActivity)
                )
                adapter?.notifyItemInserted(personList.size - 1)
            }
        }
    }

    private fun getData(){
        with(getSharedPreferences(CONTACT_INFORMATION, Context.MODE_PRIVATE)){
            val nameInfo = getString(NAME,null)
            val phoneInfo = getString(PHONE,null)
            val emailInfo = getString(EMAIL,null)
            val birthInfo = getString(BIRTH,null)
            val genderInfo = getString(GENDER_TYPE,null)
            val noteInfo = getString(NOTE,null)
            if(nameInfo != null && phoneInfo != null){
                val person = Person(nameInfo, phoneInfo, emailInfo, birthInfo, genderInfo, noteInfo)
                if (!personList.contains(person)) {
                    personList.add(person)
                }
            }
        }
    }
}
class PersonAdapter(
    val personList: MutableList<Person>,
    val inflater: LayoutInflater
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val text_name: TextView = itemView.findViewById(R.id.name)
        val text_initial: TextView = itemView.findViewById(R.id.nameinitial)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.person_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val person = personList[position]
        val viewHolder = holder as ViewHolder
        viewHolder.text_name.text = person.name
        viewHolder.text_initial.text = person.name[0].toString()

        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, ContactDetailActivity::class.java).apply {
                putExtra(NAME, person.name)
                putExtra(PHONE, person.phone)
                putExtra(EMAIL, person.email)
                putExtra(BIRTH, person.birth)
                putExtra(GENDER_TYPE, person.gender)
                putExtra(NOTE, person.note)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}
