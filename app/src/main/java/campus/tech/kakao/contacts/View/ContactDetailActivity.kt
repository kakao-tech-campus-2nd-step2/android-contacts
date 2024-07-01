package campus.tech.kakao.contacts.View

import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.contacts.Model.Contact
import campus.tech.kakao.contacts.Model.Gender
import campus.tech.kakao.contacts.R
import campus.tech.kakao.contacts.Repository.ContactRepository
import campus.tech.kakao.contacts.Util.Constant
import campus.tech.kakao.contacts.Util.DateFormatter
import java.util.Date

class ContactDetailActivity : AppCompatActivity() {
    private lateinit var error : TextView
    private lateinit var memoLayout : RelativeLayout
    private lateinit var birthdayLayout : RelativeLayout
    private lateinit var genderLayout : RelativeLayout
    private lateinit var mailLayout : RelativeLayout
    private lateinit var name : TextView
    private lateinit var tel : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        error = findViewById<TextView>(R.id.error)
        memoLayout = findViewById<RelativeLayout>(R.id.memoLayout)
        birthdayLayout = findViewById<RelativeLayout>(R.id.birthdayLayout)
        genderLayout = findViewById<RelativeLayout>(R.id.genderLayout)
        mailLayout = findViewById<RelativeLayout>(R.id.mailLayout)
        name = findViewById<TextView>(R.id.name)
        tel = findViewById<TextView>(R.id.tel)

        when(val position = intent.extras?.getInt(Constant.NAV_KEY_POSITION)){
            null -> showError()
            else -> {
                loadContact(position)
            }
        }

    }

    fun handleMail(mail : String?){
        if(!mail.isNullOrEmpty()){
            mailLayout.visibility = VISIBLE
            val mailView = findViewById<TextView>(R.id.mail)
            mailView.text = mail

        }
    }

    fun handleBirthday(birthday : Date?){
        if(birthday != null){
            birthdayLayout.visibility = VISIBLE
            val birthdayView = findViewById<TextView>(R.id.birthday)
            birthdayView.text = DateFormatter.dateToString(birthday)

        }
    }

    fun handleGender(gender: Gender?){
        if(gender != null){
            genderLayout.visibility = VISIBLE
            val genderView = findViewById<TextView>(R.id.gender)
            genderView.text = when(gender){
                Gender.MALE -> "남성"
                Gender.FEMALE -> "여성"
            }

        }
    }

    fun handleMemo(memo : String?){
        if(!memo.isNullOrEmpty()){
            memoLayout.visibility = VISIBLE
            val memoView = findViewById<TextView>(R.id.memo)
            memoView.text = memo

        }
    }


    fun loadContact(position : Int){
        val contact = ContactRepository.contactList[position]
        name.text = contact.name
        tel.text = contact.tel

        handleMail(contact.mail)
        handleBirthday(contact.bDay)
        handleGender(contact.gender)
        handleMemo(contact.memo)
    }

    fun showError() {
        error.visibility = VISIBLE
    }
}