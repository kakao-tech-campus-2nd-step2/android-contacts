package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog //날짜 선택
import java.text.SimpleDateFormat // 날짜 형식 지정
import java.util.*
import android.widget.*
import android.content.Intent

class ContactAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_add)

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

        cancelButton.setOnClickListener {
            Toast.makeText(this,"취소되었습니다.", Toast.LENGTH_SHORT).show()
            finish() //main 화면으로 돌아가기
        }

        saveButton.setOnClickListener {
            val name = nameText.text.toString().trim()
            val phone = phoneText.text.toString().trim()
            val email = emailText.text.toString().trim()
            val birth = birthText.text.toString().trim()
            val memo = memoText.text.toString().trim()

            //이름 or 전화번호 미 입력 시 저장 불가
            if(name.isEmpty() || phone.isEmpty()){
                Toast.makeText(this, "이름과 전화번호는 필수 입력 값입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //성별 선택
            //아무런 성별도 선택하지 않은 경우 " " 값이 default 이어야 함
            val gender = when(genderRadio.checkedRadioButtonId){
                R.id.femaleButton -> "여성"
                R.id.maleButton -> "남성"
                else -> " "
            }

            //contact 객체 생성
            val contact = Contact(name, phone, email, birth, gender, memo)

            //intent로 결과 반환
            val resultIntent = Intent()
            resultIntent.putExtra("contact", contact)
            setResult(RESULT_OK, resultIntent)

            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
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

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, day ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, day)
                val setting = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
                val formDate = setting.format(selectedDate.time)
                findViewById<TextView>(R.id.birthText).text = formDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }
}

