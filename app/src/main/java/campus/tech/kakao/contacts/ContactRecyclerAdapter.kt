package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ContactRecyclerAdapter(
    var contactList: MutableList<Contact>,
    val inflater: LayoutInflater,
    val mContext: Context
) : RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder>() {
    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemLayout: ConstraintLayout
        val profile: TextView
        val name: TextView
        init {
            itemLayout = itemView.findViewById(R.id.item_contact)
            itemLayout.setOnClickListener { showContactInfo(mContext, adapterPosition) }
            profile = itemView.findViewById(R.id.contact_item_profile)
            name = itemView.findViewById(R.id.contact_item_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = inflater.inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.profile.text = contactList[position].name[0].toString()
        holder.name.text = contactList[position].name
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    private fun showContactInfo(mContext: Context, adapterPosition: Int) {
        val contactIntent: Intent = Intent(mContext, ContactInfoActivity::class.java)
        contactIntent.putExtra(Contact.KEY, contactList[adapterPosition])
        mContext.startActivity(contactIntent)
    }
}