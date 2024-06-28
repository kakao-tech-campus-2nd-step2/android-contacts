package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
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
        initVar()
        initListener()
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finishContactAdding(this@MainActivity)
        }
    }

    fun finishContactAdding(context: Context){
        if(isNowWritting())
            displayCancelDialog(context)
        else
            finish()
    }


    fun displayCancelDialog(context: Context){
        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("")
        alert.setMessage("작성중인 내용이 있습니다. 정말 나가시겠습니까?")
        alert.setPositiveButton("작성하기", DialogInterface.OnClickListener{ dialog, which ->
        })
        alert.setNegativeButton("나가기", DialogInterface.OnClickListener { dialog, which ->
            displayCancelToastMessage(context)
            finish()
        })
        alert.show()
    }

    fun isNowWritting() : Boolean{
        return if(name.text.toString() != "" || phoneNumber.text.toString() != "" || mail.text.toString() != "" || birthday.text.toString() != "" || gender.text.toString() != "" || memo.text.toString() != "")
            true
        else false
    }

    fun initListener(){
        saveButtonSetOnClickListener(this)
        cancelButtonSetOnClickListener(this)
        moreButtonSetOnClickListener()
        birthdayFieldSetOnclickListener()
        genderFieldSetOnChangeListener()
    }

    fun cancelButtonSetOnClickListener(context : Context){
        findViewById<TextView>(R.id.button_cancel).setOnClickListener(){
            displayCancelToastMessage(context)
        }
    }

    fun displayCancelToastMessage(context: Context){
        val cancelMessage = Toast.makeText(context, "취소 되었습니다", Toast.LENGTH_SHORT)
        cancelMessage.show()
    }

    fun saveButtonSetOnClickListener(context : Context){
        findViewById<TextView>(R.id.button_save).setOnClickListener(){
            saveContact(context)
        }
    }

    fun existNameAndPhoneNum() : Boolean{
        val checkNum = checkNameAndPhoneNum()
        return if(checkNum == 2 || checkNum == 3) false
        else true
    }

    fun saveContact(context:Context){
        if (existNameAndPhoneNum()){
            displaySaveMessage(context)
            intent.putExtra("name", name.text.toString())
            intent.putExtra("phoneNumber", phoneNumber.text.toString())
            intent.putExtra("mail", mail.text.toString())
            intent.putExtra("birthday", birthday.text.toString())
            intent.putExtra("gender", gender.text.toString())
            intent.putExtra("memo", memo.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
        else {
            displaySaveMessage(context)
        }

    }

    fun displaySaveMessage(context: Context){
        val text = getToastMessageToNameAndPhoneNum()
        val saveMessage = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        saveMessage.show()
    }


    fun checkNameAndPhoneNum() : Int{
        return if (name.text.toString() == ""){
            name.requestFocus()
            2
        } else if(phoneNumber.text.toString() == ""){
            phoneNumber.requestFocus()
            3
        } else{
            1
        }
    }

    fun getToastMessageToNameAndPhoneNum() : String{
        val checkNum = checkNameAndPhoneNum()
        return when(checkNum){
            1 -> "저장이 완료되었습니다"
            2 -> "이름을 입력해 주세요"
            3 -> "전화번호를 입력해 주세요"
            else -> "오류"
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

    fun birthdayFieldSetOnclickListener(){
        birthday.setOnClickListener(){
            setBirthday()
        }
    }

    fun setBirthday(){
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            _, year, month, day ->
            birthday.setText("${year}/${month}/${day}")
        }, 2000, 1, 1).show()
    }

    fun genderFieldSetOnChangeListener(){
        genderRadioGroup.setOnCheckedChangeListener { _, id ->
            fillGenderField(id)
        }
    }
    fun fillGenderField(id : Int){
        when(id){
            R.id.button_woman -> gender.setText("여성")
            R.id.button_man -> gender.setText("남성")
            else -> gender.setText("")
        }
    }

    fun initVar(){
        name = findViewById<EditText>(R.id.input_name)
        phoneNumber = findViewById<EditText>(R.id.input_phone_number)
        mail = findViewById<EditText>(R.id.input_mail)
        birthday = findViewById<EditText>(R.id.input_birthday)
        gender = findViewById<EditText>(R.id.input_gender)
        genderRadioGroup = findViewById<RadioGroup>(R.id.gender_radio_group)
        memo = findViewById<EditText>(R.id.input_memo)
        moreInfoButton = findViewById<TextView>(R.id.more_info_button)
    }
}

