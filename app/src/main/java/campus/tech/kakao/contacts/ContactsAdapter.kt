package campus.tech.kakao.contacts

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
class ContactsAdapter (
    //contact 객체 데이터 저장
    val contacts : List<Contact>,
    val clickListner : (Contact) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>(){

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val contactInitial = itemView.findViewById<TextView>(R.id.contactInitial)
        val contactName = itemView.findViewById<TextView>(R.id.contactName)

        //연락처 데이터 view 바인딩
        fun bind(contact: Contact, clickListener: (Contact) -> Unit) {
            //연락처 초기 문자 설정
            val contactFirstChar = contact.name.first().toString()
            contactInitial.text = contactFirstChar

            //연락처 이름 설정
            val contactFullName = contact.name
            contactName.text = contactFullName

            // 클릭 리스너 설정
            itemView.setOnClickListener {
                clickListener(contact)
            }
        }
    }
    //viewholder 객체 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder{
        // layoutinflater 객체 생성
        val inflater = LayoutInflater.from(parent.context)
        //xml 레이아웃 파일 -> view 객체
        val itemLayout = R.layout.contact_item
        //inflate 메서드 호출 -> view 객체 생성
        val createView = false
        val view = inflater.inflate(itemLayout, parent, createView)

        return ContactViewHolder(view)
    }

    //데이터 바인딩하기
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position], clickListner)
    }

    //RecyclerView 개수, contacts 리스트 크기 반환
    override fun getItemCount(): Int = contacts.size

}