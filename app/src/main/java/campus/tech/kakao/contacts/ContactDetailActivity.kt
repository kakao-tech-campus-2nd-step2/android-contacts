package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.contacts.database.Contact

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var detailName: TextView
    private lateinit var detailPhone: TextView
    private lateinit var detailEmail: TextView
    private lateinit var detailBirthday: TextView
    private lateinit var detailGender: TextView
    private lateinit var detailMemo: TextView
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        initViews()
        val contact = getContactFromIntent()
        displayContactDetails(contact)
        setBackButtonListener()
    }

    private fun initViews() {
        detailName = findViewById(R.id.detailName)
        detailPhone = findViewById(R.id.detailPhone)
        detailEmail = findViewById(R.id.detailEmail)
        detailBirthday = findViewById(R.id.detailBirthday)
        detailGender = findViewById(R.id.detailGender)
        detailMemo = findViewById(R.id.detailMemo)
        backButton = findViewById(R.id.ic_back)
    }

    private fun getContactFromIntent(): Contact {
        return intent.getSerializableExtra("contact") as Contact
    }

    private fun displayContactDetails(contact: Contact) {
        detailName.text = contact.name
        detailPhone.text = contact.phone
        detailEmail.text = contact.email
        detailBirthday.text = contact.birthday
        detailGender.text = contact.gender
        detailMemo.text = contact.memo
    }

    private fun setBackButtonListener() {
        backButton.setOnClickListener {
            startActivity(Intent(this, ContactListActivity::class.java))
        }
    }
}
