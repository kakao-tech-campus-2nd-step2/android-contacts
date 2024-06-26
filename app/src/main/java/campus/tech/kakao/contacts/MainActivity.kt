package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog //날짜 선택
import java.text.SimpleDateFormat // 날짜 형식 지정
import java.util.*
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameText = findViewById<EditText>(R.id.nameText)
        val phoneText = findViewById<EditText>(R.id.phoneText)
        val emailText = findViewById<EditText>(R.id.emailText)
        val birthText = findViewById<TextView>(R.id.birthText)
        val memoText = findViewById<EditText>(R.id.memoText)
        val moreLayout = findViewById<LinearLayout>(R.id.moreLayout)
        val moreText = findViewById<TextView>(R.id.moreText)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val genderRadio = findViewById<RadioGroup>(R.id.genderRadio)
        val femaleButton = findViewById<RadioButton>(R.id.femaleButton)
        val maleButton = findViewById<RadioButton>(R.id.maleButton)

        //호출
        moreText.setOnClickListener{
            toggleMore()
        }

        birthText.setOnClickListener{
            showDatePicker()
        }

        //이름 or 전화번호 미 입력 시 저장 불가
        if(name.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "이름과 전화번호는 필수 입력 값입니다.", Toast.LENGTH_SHORT).show()
            return
        }
    }

    //더보기 토글 기능 - 표시 및 숨기기
    private fun toggleMore(){
        val moreLayout = findViewById<LinearLayout>(R.id.moreLayout)
        val moreText = findViewById<TextView>(R.id.moreText)

        if(moreLayout.visibility == LinearLayout.GONE){
            moreLayout.visibility = LinearLayout.VISIBLE
            moreText.visibility = TextView.GONE
        }
        else {
            moreLayout.visibility = LinearLayout.GONE
            moreText.visibility = TextView.VISIBLE
        }
    }
}
