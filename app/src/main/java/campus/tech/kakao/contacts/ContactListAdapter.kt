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
    val context: Context,
    private val contactList: MutableList<Contact>,
    private val listener: (view: View, index: Int) -> Unit = { _, _ -> }
) :
    RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textInsideImage: TextView
        private val contactName: TextView
        fun bind(contact: Contact) {
            contactName.text = contact.name
            textInsideImage.text = (contact.name).getOrNull(0).toString()
        }

        init {
            contactName = itemView.findViewById(R.id.contact_name)
            textInsideImage = itemView.findViewById(R.id.text_inside_icon)
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