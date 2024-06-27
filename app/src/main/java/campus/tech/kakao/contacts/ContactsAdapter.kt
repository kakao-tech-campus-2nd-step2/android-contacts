package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(
    private val contactsList: List<Contact>,
    private val itemClickListener: (Contact) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val profile : ImageView = itemView.findViewById(R.id.profile)
        private val nameText : TextView = itemView.findViewById(R.id.name)
        private val phoneText : TextView = itemView.findViewById(R.id.phoneNumber)

        fun bind(contact: Contact) {
            profile.setImageResource(contact.profileImage)
            nameText.text = contact.name
            phoneText.text = contact.phoneNumber
            itemView.setOnClickListener { itemClickListener(contact) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}