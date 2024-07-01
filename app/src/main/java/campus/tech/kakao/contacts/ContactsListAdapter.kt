package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsListAdapter(
	val contactsList: MutableList<ContactData>,
	val inflater: LayoutInflater,
	val context: Context
) : RecyclerView.Adapter<ContactsListAdapter.MyViewHolder>() {

	inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val thumbnail: TextView = itemView.findViewById(R.id.contact_item_thumbnail)
		val name: TextView = itemView.findViewById(R.id.contact_item_name)

		init {
			itemView.setOnClickListener {
				val intent = Intent(context, ContactInfoActivity::class.java)
				intent.putExtra("contactData", contactsList[adapterPosition])
				context.startActivity(intent)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		val view = inflater.inflate(R.layout.item_contact, parent, false)
		return MyViewHolder(view)
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		holder.thumbnail.text = contactsList[position].name[0].toString()
		holder.name.text = contactsList[position].name
	}

	override fun getItemCount(): Int = contactsList.size
}