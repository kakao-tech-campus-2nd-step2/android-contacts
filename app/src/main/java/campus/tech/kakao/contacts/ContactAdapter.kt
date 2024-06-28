package campus.tech.kakao.contacts

import android.widget.ExpandableListView.OnChildClickListener
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ContactAdapter (private val contacts: List<Contact>, private val clickListener: (Contact) -> Unit) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val initial: TextView = itemView.findViewById(R.id.contactInitial)
        val name: TextView = itemView.findViewById(R.id.contactName)

        fun bind(contact: Contact, clickListener: (Contact) -> Unit) {
            initial.text = contact.name.first().toString()
            name.text = contact.name
            itemView.setOnClickListener { clickListener(contact) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_contact_card, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position], clickListener)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}
