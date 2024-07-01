package campus.tech.kakao.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView를 이용한 연락처 목록 표시
 *
 * https://wooooooak.github.io/android/2019/03/28/recycler_view/
 *
 * 호출 시점
 * onCreateViewHolder: 처음 화면에 보이는 항목들이 생성될 때 호출됩니다.
 * onBindViewHolder: 화면에 보이는 각 항목이 다시 그려질 때 호출됩니다. 예를 들어, 스크롤하여 항목이 화면에서 사라지고 다시 보일 때 호출될 수 있습니다.
 * getItemCount: RecyclerView가 초기화될 때 한 번 호출되며, 화면에 보이는 항목의 개수를 결정하는 데 사용됩니다.
 */

class ContactAdapter(private val contacts: List<ContactDTO>, private val onClick: (ContactDTO) -> Unit)
    : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
        holder.itemView.setOnClickListener { onClick(contact) }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}
