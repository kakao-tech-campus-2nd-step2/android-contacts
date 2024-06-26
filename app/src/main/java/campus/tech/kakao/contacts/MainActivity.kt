package campus.tech.kakao.contacts

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

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
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val memo = findViewById<EditText>(R.id.memo)
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)


        // 더보기 버튼 클릭 리스너 설정
        showMoreBtn.setOnClickListener {
            showMore.visibility = LinearLayout.VISIBLE
            showMoreBtn.visibility = Button.GONE
        }

        // 성별 onCheckedChange 체크 변경 리스너
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<Button>(checkedId)
            radioGender.setText(radioButton.text)
        }

        /*
        * radioGroup에 체크 변경 리스너를 설정
        * 이 리스너는 { group, checkedId -> } 람다 표현식으로 group은 RadioGroup 객체를 참조하고,
        * checkedId는 선택된 라디오 버튼의 ID (Int 형태)
        */


        // 저장 버튼 리스너
        saveBtn.setOnClickListener {
            // 이름과 전화번호가 입력되지 않았을 때 토스트 메시지 표시
            if (name.text.isNullOrEmpty() || phone.text.isNullOrEmpty()) {
                Toast.makeText(this, "이름과 전화번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 저장 로직
                Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 취소 버튼 리스너
        cancelBtn.setOnClickListener {
            // 취소 로직
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show()
        }

    }
}
