package campus.tech.kakao.contacts

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)
        val infoContact =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                intent.extras?.getSerializable(Contact.KEY,Contact::class.java)
            else
                intent.extras?.getSerializable(Contact.KEY) as Contact?

        if (infoContact is Contact) {
            val infoProfile: TextView = findViewById(R.id.info_profile)
            val infoName: TextView = findViewById(R.id.info_name)
            val infoPhoneNumber: TextView = findViewById(R.id.info_phone_number)
            val infoMail: TextView = findViewById(R.id.info_mail)
            val infoBirth: TextView = findViewById(R.id.info_birth)
            val infoSex: TextView = findViewById(R.id.info_sex)
            val infoMemo: TextView = findViewById(R.id.info_memo)

            matchInfo(infoProfile, infoName, infoPhoneNumber, infoMail, infoBirth, infoSex, infoMemo, infoContact)
        }
    }

    private fun matchInfo(infoProfile: TextView, infoName: TextView, infoPhoneNumber: TextView, infoMail: TextView,
                          infoBirth: TextView, infoSex: TextView, infoMemo: TextView, infoContact: Contact) {

        infoProfile.text = infoContact.name[0].toString()
        infoName.text = infoContact.name
        infoPhoneNumber.text = infoContact.phoneNumber
        infoMail.text = infoContact.mail
        infoBirth.text = infoContact.birth
        infoSex.text =
            when (infoContact.sex) {
                infoContact.FEMALE -> "여"
                infoContact.MALE -> "남"
                else -> ""
            }
        infoMemo.text = infoContact.memo
    }
}