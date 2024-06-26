package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.json.JSONObject

class ContactDetailActivity : AppCompatActivity() {
    private var contactData: JSONObject? = null
    private lateinit var name: TextView
    private lateinit var phoneNumber: TextView
    private lateinit var mail: TextView
    private lateinit var groupMail: Group
    private lateinit var birthday: TextView
    private lateinit var groupBirthday: Group
    private lateinit var gender: TextView
    private lateinit var groupGender: Group
    private lateinit var memo: TextView
    private lateinit var groupMemo: Group
    private lateinit var profile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        initViews()
        setContactData()
        updateViews()
    }

    private fun initViews() {
        profile = findViewById(R.id.iv_profile)
        name = findViewById(R.id.tv_name)
        phoneNumber = findViewById(R.id.tv_phone_number)
        mail = findViewById(R.id.tv_mail)
        groupMail = findViewById(R.id.group_mail)
        birthday = findViewById(R.id.tv_birthday)
        groupBirthday = findViewById(R.id.group_birthday)
        gender = findViewById(R.id.tv_gender)
        groupGender = findViewById(R.id.group_gender)
        memo = findViewById(R.id.tv_memo)
        groupMemo = findViewById(R.id.group_memo)
    }

    private fun setContactData() {
        contactData = JSONObject(intent.getStringExtra(CONTACT_DATA).orEmpty())

        contactData?.apply {
            getString(CONTACT_NAME).orEmpty().let { name.text = it }
            getString(CONTACT_PHONE_NUMBER).orEmpty().let { phoneNumber.text = it }
            getString(CONTACT_MAIL).orEmpty().let {
                groupMail.isVisible = it.isNotEmpty()
                mail.text = it
            }
            getString(CONTACT_BIRTHDAY).orEmpty().let {
                groupBirthday.isVisible = it.isNotEmpty()
                birthday.text = it
            }
            getString(CONTACT_GENDER).orEmpty().let {
                groupGender.isVisible = it.isNotEmpty()
                when (it) {
                    GENDER_FEMALE -> {
                        profile.setColorFilter(
                            ContextCompat.getColor(
                                baseContext,
                                R.color.red,
                            ),
                        )
                        gender.text = getString(R.string.female)
                    }

                    GENDER_MALE -> {
                        profile.setColorFilter(
                            ContextCompat.getColor(
                                baseContext,
                                R.color.blue,
                            ),
                        )
                        gender.text = getString(R.string.mail)
                    }
                }
            }
            getString(CONTACT_MEMO).orEmpty().let {
                groupMemo.isVisible = it.isNotEmpty()
                memo.text = it
            }
        }
    }

    private fun updateViews() {
        if (contactData == null) return

        contactData?.apply {
            name.text = if (has(CONTACT_NAME)) getString(CONTACT_NAME) else ""
        }
    }
}
