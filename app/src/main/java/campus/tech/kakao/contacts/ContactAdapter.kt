package campus.tech.kakao.contacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.Contact
import com.example.contacts.ContactDetailActivity

class ContactAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileView: TextView = view.findViewById(R.id.profileView)
        val nameView: TextView = view.findViewById(R.id.nameView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameView.text = contact.name
        holder.profileView.text = contact.name.first().toString()
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ContactDetailActivity::class.java).apply {
                putExtra("name", contact.name)
                putExtra("phone", contact.phone)
                putExtra("email", contact.email)
                putExtra("birthday", contact.birthday)
                putExtra("gender", contact.gender)
                putExtra("memo", contact.memo)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = contacts.size
}
