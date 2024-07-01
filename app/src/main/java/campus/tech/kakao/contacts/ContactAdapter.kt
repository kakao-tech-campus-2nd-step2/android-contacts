package campus.tech.kakao.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ContactAdapter(private val context: Context, private val contacts: List<Contact>) : BaseAdapter() {

    override fun getCount(): Int = contacts.size

    override fun getItem(position: Int): Any = contacts[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)
            holder = ViewHolder()
            holder.nameTextView = view.findViewById(R.id.nameTextView)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val contact = contacts[position]
        holder.nameTextView?.text = contact.name

        return view
    }

    private class ViewHolder {
        var nameTextView: TextView? = null
    }
}
