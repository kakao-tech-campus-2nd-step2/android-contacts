package campus.tech.kakao.contacts

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar


/**
 * 연락처 등록
 */

class ContactAddActivity : AppCompatActivity() {


    /**
     * 질문, private lateinit 이렇게 하는 방법을 알았는데 함수를 관리하기 쉽다는 장점을 알았습니다. 단점이 있을까요?
     */

    /****
     * lateinit은 해당 변수에 나중에 객체를 할당하는 것이기 때문에 무조건 var과 써야 한다.
     * val은 참조를 변경할 수 없는 변수 선언 방식이므로 사용할 수 없다.
     */

    // 뷰 초기화
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var mail: EditText
    private lateinit var showMoreBtn: Button
    private lateinit var showMore: LinearLayout
    private lateinit var birth: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var memo: EditText
    private lateinit var saveBtn: Button
    private lateinit var cancelBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_add)

        // 뷰 초기화
        initializeViews()

        // 더보기 버튼 클릭 리스너 설정
        showMoreBtn.setOnClickListener {
            showMore.visibility = LinearLayout.VISIBLE
            showMoreBtn.visibility = Button.GONE
        }

        // 생일 클릭 리스너 설정
        birth.setOnClickListener {
            showDatePickerDialog()
        }

        // 저장 버튼 리스너
        saveBtn.setOnClickListener {
            saveContact()
        }

        // 취소 버튼 리스너
        cancelBtn.setOnClickListener {
            showCancelConfirmationDialog()
        }

        // 뒤로 가기 버튼 처리
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showCancelConfirmationDialog()
            }
        })

    }


    // 뷰 초기화 메서드
    private fun initializeViews() {
        name = findViewById(R.id.name)
        phone = findViewById(R.id.phone)
        mail = findViewById(R.id.mail)
        showMoreBtn = findViewById(R.id.showMoreBtn)
        showMore = findViewById(R.id.showMore)
        birth = findViewById(R.id.birthday)
        radioGroup = findViewById(R.id.radioGroup)
        memo = findViewById(R.id.memo)
        saveBtn = findViewById(R.id.saveBtn)
        cancelBtn = findViewById(R.id.cancelBtn)
    }


    // 생일 선택 다이얼로그 표시
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "$selectedYear.${selectedMonth + 1}.$selectedDay"
                birth.setText(formattedDate)
            }, year, month, day)
        datePickerDialog.show()
    }


    /** 질문, 취소 버튼과 뒤로 가기 버튼이 각자 다른 기능을 하는데
     * 같은 기능을 하게 만드려면 따로 만들어야 하나? **/
    // 취소 확인 다이얼로그 표시
    private fun showCancelConfirmationDialog() {
        AlertDialog.Builder(this)
            .setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
            .setPositiveButton("작성하기") { dialog, which -> }
            .setNegativeButton("나가기") { dialog, which ->
                Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            .show()
    }


    // 연락처 저장
    private fun saveContact() {

        // 선택된 라디오 버튼의 텍스트 가져오기
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
        val gender = selectedRadioButton?.text.toString()

        // 이름과 전화번호가 입력되지 않았을 때 토스트 메시지 표시
        if (name.text.isNullOrEmpty() || phone.text.isNullOrEmpty()) {
            Toast.makeText(this, "이름과 전화번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
        } else {
            val resultIntent = Intent().apply {
                // putExtra( 키 , 값 ) <==> getStringExtra( 키 , 값 ) 키가 같아야 됨
                putExtra("contact_name", name.text.toString())
                putExtra("contact_phone", phone.text.toString())
                putExtra("contact_mail", mail.text.toString())
                putExtra("contact_birth", birth.text.toString())
                putExtra("contact_gender", gender)
                putExtra("contact_memo", memo.text.toString())
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
