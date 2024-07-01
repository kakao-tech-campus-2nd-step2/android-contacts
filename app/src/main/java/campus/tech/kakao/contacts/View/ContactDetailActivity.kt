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
import campus.tech.kakao.contacts.Util.DateFormatter
import java.util.Date

class ContactDetailActivity : AppCompatActivity() {

    fun handleMail(mail : String?){
        val mailLayout = findViewById<RelativeLayout>(R.id.mailLayout)
        if(!mail.isNullOrEmpty()){
            mailLayout.visibility = VISIBLE
            val mailView = findViewById<TextView>(R.id.mail)
            mailView.text = mail

        }
    }

    fun handleBirthday(birthday : Date?){
        val birthdayLayout = findViewById<RelativeLayout>(R.id.birthdayLayout)
        if(birthday != null){
            birthdayLayout.visibility = VISIBLE
            val birthdayView = findViewById<TextView>(R.id.birthday)
            birthdayView.text = DateFormatter.dateToString(birthday)

        }
    }

    fun handleGender(gender: Gender?){
        val genderLayout = findViewById<RelativeLayout>(R.id.genderLayout)
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
        val memoLayout = findViewById<RelativeLayout>(R.id.memoLayout)
        if(!memo.isNullOrEmpty()){
            memoLayout.visibility = VISIBLE
            val memoView = findViewById<TextView>(R.id.memo)
            memoView.text = memo

        }
    }


    fun loadContact(contact: Contact){
        val name = findViewById<TextView>(R.id.name)
        val tel = findViewById<TextView>(R.id.tel)
        name.text = contact.name
        tel.text = contact.tel


        handleMail(contact.mail)
        handleBirthday(contact.bDay)
        handleGender(contact.gender)
        handleMemo(contact.memo)
    }

    fun showError(){
        val error = findViewById<TextView>(R.id.error)
        error.visibility = VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        when(val position = intent.extras?.getInt("position")){
            null -> showError()
            else -> {
                val contact = ContactRepository.contactList[position]
                loadContact(contact)
            }
        }

    }
}