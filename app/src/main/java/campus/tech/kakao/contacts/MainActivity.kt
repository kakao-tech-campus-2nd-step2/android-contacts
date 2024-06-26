package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSave: TextView = findViewById<TextView>(R.id.btnSave)
        val btnCancel: TextView = findViewById<TextView>(R.id.btnCancel)
        val btnMore: ImageView = findViewById<ImageView>(R.id.btnMore)
        val inputName: EditText = findViewById<EditText>(R.id.inputName)
        val inputPhoneNumber: EditText = findViewById<EditText>(R.id.inputPhoneNumber)
        val radioGroupGender: RadioGroup = findViewById<RadioGroup>(R.id.radioGroupGender)
        val additionalInputLayout: ConstraintLayout =
        findViewById<ConstraintLayout>(R.id.additionalInputLayout)


        val inputTextList = listOf(
            inputName,
            inputPhoneNumber,
            findViewById<EditText>(R.id.inputBirthDay),
            findViewById<EditText>(R.id.inputEmail),
            findViewById<EditText>(R.id.inputGender),
            findViewById<EditText>(R.id.inputMemo),
        )
        
        btnSave.setOnClickListener {
            if (inputName.text.isEmpty() || inputPhoneNumber.text.isEmpty()){
                Toast.makeText(this, "이름 또는 전화번호를 입력하세요", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancel.setOnClickListener {
            if (isInputFormFilled(inputTextList,radioGroupGender)){
                showAlertDialog()
            }else{
                Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        btnMore.setOnClickListener {
            btnMore.visibility = View.GONE
            additionalInputLayout.visibility = View.VISIBLE
        }
    }
    private fun isInputFormFilled(inputTextList: List<EditText>, radioGroup: RadioGroup ):Boolean{
        inputTextList.forEach{
            if (it.text.isNotEmpty()){
                return true
            }
            if (radioGroup.checkedRadioButtonId != -1){
                return true
            }
        }
        return false
    }

    private fun showAlertDialog(){
        AlertDialog.Builder(this)
            .setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
            .setPositiveButton("예"){dialog,which->
                Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("아니오",null)
            .show()
    }
}
