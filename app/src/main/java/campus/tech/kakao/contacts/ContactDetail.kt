package campus.tech.kakao.contacts

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ContactDetail : AppCompatActivity() {
    lateinit var detailName: TextView
    lateinit var detailTel: TextView
    lateinit var detailMail: TextView
    lateinit var detailBirth: TextView
    lateinit var detailGender: TextView
    lateinit var detailMemo: TextView

    lateinit var detailMailLayout: ConstraintLayout
    lateinit var detailBirthLayout: ConstraintLayout
    lateinit var detailGenderLayout: ConstraintLayout
    lateinit var detailMemoLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        initialize()
        setContactDetail()
    }
    private fun initialize() {
        detailName = findViewById(R.id.detailName)
        detailTel = findViewById(R.id.detailTel)
        detailMail = findViewById(R.id.detailMail)
        detailBirth = findViewById(R.id.detailBirth)
        detailGender = findViewById(R.id.detailGender)
        detailMemo = findViewById(R.id.detailMemo)

        detailMailLayout = findViewById(R.id.detailMail_layout)
        detailBirthLayout = findViewById(R.id.detailBirth_layout)
        detailGenderLayout = findViewById(R.id.detailGender_layout)
        detailMemoLayout = findViewById(R.id.detailMemo_layout)
    }

    private fun setContactDetail() {
        val contact: Contact? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("CONTACT", Contact::class.java)
            } else {
                intent.getParcelableExtra("CONTACT")
            }

        contact?.let {
            setDetail(it)
        }
    }
    private fun setDetail(contact: Contact) {
        detailName.text = contact.name
        detailTel.text = contact.tel
        detailMail.text = contact.mail ?: ""
        detailBirth.text = contact.birth ?: ""
        detailGender.text = contact.gender
        detailMemo.text = contact.memo ?: ""
    }

}
