package campus.tech.kakao.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.model.Contact

class ContactListAdapter(
    val inflater: LayoutInflater,
    val context: Context,
    val contactList: MutableList<Contact>
) :
    RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textInsideImage: TextView
        val contactName: TextView
        fun bind(contact: Contact){
            contactName.text = contact.name
            textInsideImage.text = (contact.name).getOrNull(0).toString()
        }

        init{
            contactName = itemView.findViewById(R.id.contact_name)
            textInsideImage = itemView.findViewById(R.id.text_inside_icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = inflater.inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }
}