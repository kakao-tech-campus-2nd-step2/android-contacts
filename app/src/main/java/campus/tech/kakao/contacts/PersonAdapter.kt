package campus.tech.kakao.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter (
    private val personList: List<Person>,
    private val clickListener: (Person) -> Unit
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.input_name)
        val telTextView: TextView = view.findViewById(R.id.input_tel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_view_contact, parent, false) as View
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.nameTextView.text = person.name
        holder.telTextView.text = person.tel
        holder.itemView.setOnClickListener { clickListener(person) }
    }
    
}