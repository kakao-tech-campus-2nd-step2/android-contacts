package campus.tech.kakao.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val contacts: MutableList<Contact>,
    private val onItemClickListener: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ContactViewHolder(
        view: View,
        private val onItemClickListener: (Contact) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val nameView: TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(contact: Contact) {
            nameView.text = contact.name
            itemView.setOnClickListener {
                onItemClickListener(contact)
            }
        }
    }
}
