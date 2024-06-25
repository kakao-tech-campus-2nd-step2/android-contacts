package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var emailInput:EditText
    lateinit var nameInput:EditText
    lateinit var phoneInput:EditText
    lateinit var birthdayInput:EditText
    var gender:Int = -1
    lateinit var memoInput:EditText

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcontact)
        initiateInputFields()
        setMoreOptionsListener()
        setButtonsListener()
    }

    fun initiateInputFields(){
        nameInput = findViewById(R.id.input_name)
        emailInput = findViewById(R.id.input_mail)
        phoneInput = findViewById(R.id.input_tel)
        birthdayInput = findViewById(R.id.input_birthday)
        memoInput = findViewById(R.id.input_memo)
        gender = findViewById<RadioGroup>(R.id.input_gender).checkedRadioButtonId
    }

    fun setMoreOptionsListener(){
        findViewById<View>(R.id.more_options).setOnClickListener {
            appendOptions()
        }
    }

    fun setButtonsListener(){
        findViewById<Button>(R.id.button_submit).setOnClickListener {
            if(validateInputs()){
                saveSuccess()
            }
        }
        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            cancel()
        }
    }

    fun validateInputs():Boolean{
        if(checkNameEmpty()){
            showToast("이름 값은 필수입니다")
            return false
        }
        if(checkPhoneNumberEmpty()){
            showToast("전화번호 값은 필수입니다")
            return false
        }
        if(!checkEmailEmpty()){
            if(!verifyEmail(emailInput.text.toString())){
                showToast("잘못된 이메일 형식입니다")
                return false
            }
        }
        return true
    }

    fun checkNameEmpty():Boolean{
        return nameInput.text.isEmpty()
    }

    fun checkPhoneNumberEmpty():Boolean{
        return phoneInput.text.isEmpty()
    }

    fun checkEmailEmpty():Boolean{
        return emailInput.text.isEmpty()
    }

    fun verifyEmail(emailText:String):Boolean{
        val emailVerifyingRegex = Regex("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$")
        return emailVerifyingRegex.matches(emailText)
    }

    fun appendOptions(){
        findViewById<View>(R.id.more_options).visibility = View.GONE
        findViewById<View>(R.id.additional_inputs).visibility = View.VISIBLE
    }

    fun saveSuccess(){
        showToast("저장이 완료 되었습니다")
    }

    fun cancel(){
        showToast("취소 되었습니다")
    }

    fun showToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
