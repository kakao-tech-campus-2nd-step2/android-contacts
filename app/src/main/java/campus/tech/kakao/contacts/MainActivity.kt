package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
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
                val intent = Intent()
                intent.putExtra("userData", UserData(inputName.text.toString(),
                    inputPhoneNumber.text.toString().toInt()))
                setResult(RESULT_OK,intent)
                finish()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val resultData = data?.getStringExtra("resultKey")
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
            .setPositiveButton("나가기"){dialog,which->
                Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("작성하기",null)
            .show()
    }

}
