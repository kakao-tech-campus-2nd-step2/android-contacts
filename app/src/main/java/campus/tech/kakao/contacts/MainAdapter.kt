package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(private val context: Context, private val itemList: List<MainData>) : RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val number: TextView = itemView.findViewById(R.id.number)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val intent = Intent(context, contactsInfo::class.java).apply {
                        putExtra("name", itemList[position].name)
                        putExtra("number", itemList[position].number)
                        putExtra("mail", itemList[position].mail)
                        putExtra("birthday", itemList[position].birthday)
                        putExtra("gender", itemList[position].gender)
                        putExtra("memo", itemList[position].memo)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = itemList[position]
        holder.name.text = item.name
        holder.number.text = item.number
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}