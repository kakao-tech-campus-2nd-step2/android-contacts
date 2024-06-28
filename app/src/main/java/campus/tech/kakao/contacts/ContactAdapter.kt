package campus.tech.kakao.contacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val itemList: List<Contact>,
                     var inflater: LayoutInflater
): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int) {}
    }
    var itemClickListener: OnItemClickListener? = null
    inner class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val phoneNumber: TextView

        init {
            name = itemView.findViewById<TextView>(R.id.name)
            phoneNumber= itemView.findViewById<TextView>(R.id.phoneNumber)

            itemView.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = inflater.inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.name.text = itemList[position].name
        holder.phoneNumber.text = itemList[position].phoneNumber
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}