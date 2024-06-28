package campus.tech.kakao.contacts

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView

class ContactAdapter (
    var contactList: MutableList<Contact>,
    var inflater: LayoutInflater
): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val faceIcon: ImageView
        val contactName: TextView

        init {
            faceIcon = itemView.findViewById(R.id.faceIcon)
            contactName = itemView.findViewById(R.id.contactName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.caontact_item, parent, false)
        return ContactAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contactName.text = contactList.get(position).name
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}