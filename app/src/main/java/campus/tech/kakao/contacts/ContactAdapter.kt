
package campus.tech.kakao.contacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.database.Contact

class ContactAdapter(
    private var contactList: List<Contact>,
    private val inflater: LayoutInflater
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val firstName: TextView
        val fullName: TextView

        init {
            firstName = itemView.findViewById(R.id.textViewFirstName)
            fullName = itemView.findViewById(R.id.textViewFullName)
            itemView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val context = itemView.context
                    val contact = contactList[position]
                    val intent = Intent(context, ContactDetailActivity::class.java).apply {
                        putExtra("contact", contact)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_contact, parent, false))
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactItem = contactList[position]
        (holder as ViewHolder).firstName.text = contactItem.name?.get(0)?.toString()?.toUpperCase() ?: ""
        (holder as ViewHolder).fullName.text = contactItem.name
    }
}