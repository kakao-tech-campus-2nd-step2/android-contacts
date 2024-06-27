package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰 초기화
        val name = findViewById<EditText>(R.id.name)
        val phone = findViewById<EditText>(R.id.phone)
        val mail = findViewById<EditText>(R.id.mail)
        val showMoreBtn = findViewById<Button>(R.id.showMoreBtn)
        val showMore = findViewById<LinearLayout>(R.id.showMore)
        val birth = findViewById<EditText>(R.id.birthday)
        val radioGender = findViewById<EditText>(R.id.radioGender)
        val memo = findViewById<EditText>(R.id.memo)
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)


        // 더보기 버튼 클릭 리스너 설정
        showMoreBtn.setOnClickListener {
            showMore.visibility = LinearLayout.VISIBLE
            showMoreBtn.visibility = Button.GONE
        }



        // 생일 클릭 리스너 설정
        birth.setOnClickListener {
            // 현재 날짜를 기본값으로 설정
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // 선택된 날짜를 EditText에 설정
                val formattedDate = "$selectedYear.${selectedMonth + 1}.$selectedDay"
                birth.setText(formattedDate)
            }, year, month, day)
            datePickerDialog.show()
        }




        // 저장 버튼 리스너
        saveBtn.setOnClickListener {
            // 이름과 전화번호가 입력되지 않았을 때 토스트 메시지 표시
            if (name.text.isNullOrEmpty() || phone.text.isNullOrEmpty()) {
                Toast.makeText(this, "이름과 전화번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }




        // 취소 버튼 리스너
        cancelBtn.setOnClickListener {
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show()
        }

    }
}
