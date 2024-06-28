package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
	val contact_list: List<Contact>,
	val inflater: LayoutInflater,
	val context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

	inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
		val name_first: TextView
		val name:TextView
		init {
			name_first = itemView.findViewById(R.id.nameFirst)
			name = itemView.findViewById(R.id.name)
			//클릭 리스너 생성
			itemView.setOnClickListener {
				val position = adapterPosition
				//adapterPosition을 통해 포지션을 받음.
				val contact = contact_list.get(position)
//				Toast.makeText(context, ""+contact.name+" "+contact.phone, Toast.LENGTH_SHORT).show()
				val intent = Intent(context, ContactDetail::class.java)
				intent.putExtra("contact-id", contact.id)
				startActivity(context, intent, null)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = inflater.inflate(R.layout.contact_item, parent, false)
		return ViewHolder(view)
	}

	//데이터를 아이템뷰의 뷰컴포넌트와 바인딩함.
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val name_first_word = contact_list.get(position).name[0]
		holder.name_first.text = name_first_word.toString()
		holder.name.text = contact_list.get(position).name
	}

	override fun getItemCount(): Int {
		return contact_list.size
	}
}


