package campus.tech.kakao.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.model.Contact

class ContactListAdapter(
    private val inflater: LayoutInflater,
    private val contactList: List<Contact>,
    private val listener: (view: View, index: Int) -> Unit = { _, _ -> }
) :
    RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textInsideImage: TextView = itemView.findViewById(R.id.text_inside_icon)
        private val contactName: TextView = itemView.findViewById(R.id.contact_name)

        fun bind(contact: Contact) {
            contactName.text = contact.name
            textInsideImage.text = (contact.name).firstOrNull().toString()
        }

        fun setOnViewClickListener(listener: ((view: View, index: Int) -> Unit)?) {
            itemView.setOnClickListener {
                listener?.invoke(it, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = inflater.inflate(R.layout.contact_item, parent, false)
        val holder = ContactViewHolder(view)
        holder.setOnViewClickListener(listener)
        return holder
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }
}