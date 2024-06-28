package campus.tech.kakao.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val context: Context, private val contactList: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(contact: Contact)
    }

    var itemClickListener: OnItemClickListener? = null

    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.contact_name)
        val profileInitialTextView: TextView = view.findViewById(R.id.profile_initial)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]

        // Set contact name
        holder.nameTextView.text = contact.name

        // Set initial letter in profile initial TextView
        holder.profileInitialTextView.text = contact.name.substring(0, 1).toUpperCase()

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(contact)
        }
    }

    override fun getItemCount() = contactList.size
}
