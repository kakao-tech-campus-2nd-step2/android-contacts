package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat

class Contact_List : AppCompatActivity() {
    lateinit var contactListLayout: LinearLayoutCompat
    lateinit var addContact: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        val add = findViewById<Button>(R.id.add)
        contactListLayout = findViewById<LinearLayoutCompat>(R.id.contactlist)

        addContact = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val name = data?.getStringExtra("name")
                val phone = data?.getStringExtra("phone")
                val email = data?.getStringExtra("email") ?: ""
                val birth = data?.getStringExtra("birth") ?: ""
                val gender = data?.getStringExtra("gender") ?: ""
                val memo = data?.getStringExtra("memo") ?: ""
                if (name != null && phone != null) {
                    addContactItem(name, phone, email, birth, gender, memo)
                }
            }
        }

        add.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            addContact.launch(intent)
            val notice = findViewById<TextView>(R.id.notice)
            notice.visibility = View.GONE
        }
    }

    fun addContactItem(
        name: String,
        phone: String,
        email: String,
        birthday: String,
        gender: String,
        memo: String
    ) {
        val firstname = name[0].toString()

        // 새 연락처 아이템을 생성
        val contactView =
            LayoutInflater.from(this).inflate(R.layout.activity_list_item, contactListLayout, false)

        val itemName = contactView.findViewById<TextView>(R.id.itemname)
        val profileName = contactView.findViewById<TextView>(R.id.profilename)

        itemName.text = name
        profileName.text = firstname

        contactView.setOnClickListener {
            val detailIntent = Intent(this, ContactDetail::class.java).apply {
                putExtra("name", name)
                putExtra("phone", phone)
                putExtra("email", email)
                putExtra("birthday", birthday)
                putExtra("gender", gender)
                putExtra("memo", memo)
            }
            startActivity(detailIntent)
        }
        contactListLayout.addView(contactView)

    }
}