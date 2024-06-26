package campus.tech.kakao.contacts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var name : EditText
    lateinit var phoneNumber : EditText
    lateinit var mail : EditText
    lateinit var gender : EditText
    lateinit var genderRadioGroup : RadioGroup
    lateinit var memo : EditText
    lateinit var birthday : EditText
    lateinit var moreInfoButton : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById<EditText>(R.id.input_name)
        phoneNumber = findViewById<EditText>(R.id.input_phone_number)
        mail = findViewById<EditText>(R.id.input_mail)
        birthday = findViewById<EditText>(R.id.input_birthday)
        gender = findViewById<EditText>(R.id.input_gender)
        genderRadioGroup = findViewById<RadioGroup>(R.id.gender_radio_group)
        memo = findViewById<EditText>(R.id.input_memo)
        moreInfoButton = findViewById<TextView>(R.id.more_info_button)

        saveButtonSetOnClickListener(this)
        cancelButtonSetOnClickListener(this)
        moreButtonSetOnClickListener()

    }

    fun cancelButtonSetOnClickListener(context : Context){
        findViewById<TextView>(R.id.button_cancel).setOnClickListener(){
            displayCancelMessage(context)
        }
    }

    fun displayCancelMessage(context: Context){
        val cancelMessage = Toast.makeText(context, "취소 되었습니다", Toast.LENGTH_SHORT)
        cancelMessage.show()
    }

    fun saveButtonSetOnClickListener(context : Context){
        findViewById<TextView>(R.id.button_save).setOnClickListener(){
            displaySaveMessage(context)
        }
    }

    fun displaySaveMessage(context: Context){
        val text = checkNameAndPhoneNum()
        val saveMessage = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        saveMessage.show()
    }

    fun checkNameAndPhoneNum() : String{
        if (name.text.toString() == ""){
            name.requestFocus()
            return "이름을 입력해야만 합니다"
        }
        else if(phoneNumber.text.toString() == ""){
            phoneNumber.requestFocus()
            return "번호를 입력해야만 합니다"
        }
        else{
            return "저장이 완료 되었습니다"
        }
    }

    fun moreButtonSetOnClickListener(){
        moreInfoButton.setOnClickListener(){
            setInputFieldVisible()
        }
    }

    fun setInputFieldVisible(){
        birthday.visibility = View.VISIBLE
        gender.visibility = View.VISIBLE
        genderRadioGroup.visibility = View.VISIBLE
        memo.visibility = View.VISIBLE
        moreInfoButton.visibility = View.GONE
    }
}

