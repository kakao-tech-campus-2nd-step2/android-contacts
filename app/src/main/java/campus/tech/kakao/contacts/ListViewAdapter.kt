package campus.tech.kakao.contacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import campus.tech.kakao.contacts.databinding.ContactListBarBinding

class ListViewAdapter : BaseAdapter() {

    override fun getCount(): Int = ContactList.size

    override fun getItem(position: Int): contact = ContactList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ContactListBarBinding
        val view: View

        if (convertView == null) {
            binding = ContactListBarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ContactListBarBinding
            view = convertView
        }

        val contact = getItem(position)
        binding.NameSpace.text = contact.name
        binding.OneNameSpace.text = contact.oneName
        binding.TeleSpace.text = contact.phone_number

        // 아이템 클릭 리스너 설정
        view.setOnClickListener {
            val context = parent.context
            val intent = Intent(context, DetailContact::class.java).apply {
                putExtra("name", contact.name)
                putExtra("phoneNumber", contact.phone_number)
            }
            context.startActivity(intent)
        }

        return view
    }
}
