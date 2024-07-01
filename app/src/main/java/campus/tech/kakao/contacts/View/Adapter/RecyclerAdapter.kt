package campus.tech.kakao.contacts.View.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import campus.tech.kakao.contacts.Model.Contact
import campus.tech.kakao.contacts.R
import campus.tech.kakao.contacts.View.ContactDetailActivity


class RecyclerAdapter(
    private var contactList : List<Contact>,
    private var inflater : LayoutInflater,

    ) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    class ViewHolder(context: Context, itemView : View) : RecyclerView.ViewHolder(itemView){
        val contactName : TextView
        val contactImage : TextView
        init{
            contactImage = itemView.findViewById<TextView>(R.id.contactImage)
            contactName = itemView.findViewById<TextView>(R.id.contactName)

            itemView.setOnClickListener {
                val intent = Intent(context, ContactDetailActivity::class.java)
                intent.putExtra("position",adapterPosition)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.contact_element,parent,false)

        return ViewHolder(parent.context,view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.contactName.text = contact.name
        holder.contactImage.text = contact.name[0].toString()
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}