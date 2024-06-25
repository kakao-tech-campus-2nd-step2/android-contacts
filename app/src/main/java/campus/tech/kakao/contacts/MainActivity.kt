package campus.tech.kakao.contacts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var name : EditText
    lateinit var phoneNumber : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById<EditText>(R.id.input_name)
        phoneNumber = findViewById<EditText>(R.id.input_phone_number)
        saveButtonSetOnClickListner(this)
        cancelButtonSetOnClickListner(this)
    }

    fun cancelButtonSetOnClickListner(context : Context){
        findViewById<TextView>(R.id.button_cancel).setOnClickListener(){
            displayCancelMessage(context)
        }
    }

    fun displayCancelMessage(context: Context){
        val cancelMessage = Toast.makeText(context, "취소 되었습니다", Toast.LENGTH_SHORT)
        cancelMessage.show()
    }

    fun saveButtonSetOnClickListner(context : Context){
        findViewById<TextView>(R.id.button_save).setOnClickListener(){
            displaySaveMessage(context)
        }
    }

    fun displaySaveMessage(context: Context){
        Log.d("phone", "" + name.text)
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
}

