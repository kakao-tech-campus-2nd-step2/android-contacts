package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var phoneNumEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var birthdayTextView: TextView
    lateinit var genderLayout: ConstraintLayout
    lateinit var memoEditText: EditText
    lateinit var cancelBtn: Button
    lateinit var saveBtn: Button
    lateinit var seeMoreLayout: LinearLayoutCompat
    lateinit var genderRadioGroup: RadioGroup
    private var gender = 0 // 0 : 무응답, 1 : 여성, 2 : 남성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setOnClickListeners()
    }

    /**
     * 사용할 view들을 초기화하는 함수.
     * - `nameEditText` : 이름 입력폼을 나타내는 EditText
     * - `phoneNumEditText` : 전화번호 입력폼을 나타내는 EditText
     * - `emailEditText` : 이메일 입력폼을 나타내는 EditText
     * - `birthdayTextView` : 생일 입력폼을 나타내는 TextView
     * - `genderLayout` : 성별 입력폼을 나타내는 ConstraintLayout
     * - `memoEditText` : 메모 력폼을 나타내는 EditText
     * - `cancelBtn` : 취소 버튼을 나타내는 Button
     * - `saveBtn` : 저장 버튼을 나타내는 Button
     * - `seeMoreLayout` : 더보기를 나타내는 LinearLayoutCompat
     * - `genderRadioGroup` : 성별 선택을 위한 RadioGroup
     */
    private fun initViews() {
        nameEditText = findViewById(R.id.name_edit_text)
        phoneNumEditText = findViewById(R.id.phone_num_edit_text)
        emailEditText = findViewById(R.id.email_edit_text)
        birthdayTextView = findViewById(R.id.birthday_text_view)
        genderLayout = findViewById(R.id.gender_layout)
        memoEditText = findViewById(R.id.memo_edit_text)
        cancelBtn = findViewById(R.id.cancel_btn)
        saveBtn = findViewById(R.id.save_btn)
        seeMoreLayout = findViewById(R.id.see_more_layout)
        genderRadioGroup = findViewById(R.id.gender_radio_group)
    }

    /**
     * 사용할 클릭 리스너들을 설정하는 함수
     */
    private fun setOnClickListeners() {
        setOnClickListenerOfSaveBtn()
        setOnClickListenerOfCancelBtn()
        setOnClickListenerOfSeeMoreLayout()
        setOnClickListenerOfGenderRadioGroup()
        setOnClickListenerOfBirthdayEditText()
    }

    /**
     * 취소 버튼에 대한 클릭 리스너를 설정하는 함수
     *
     * 취소 버튼을 누르면 취소 되었습니다.라는 Toast 메시지 출력.
     */
    private fun setOnClickListenerOfCancelBtn() {
        cancelBtn.setOnClickListener {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * 저장 버튼에 대한 클릭 리스너를 설정하는 함수
     *
     * 이름 또는 전화번호 입력 폼이 비어있을 경우 Toast 메시지 출력.
     * 아닌 경우 입력한 정보를 contact 객체로 담아 결과를 보내고 finish.
     *
     * - `contact` : 입력한 연락처 정보를 담고 있는 Contact 객체
     */
    private fun setOnClickListenerOfSaveBtn() {
        saveBtn.setOnClickListener {
            if (nameEditText.text.isEmpty()) {
                Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_LONG).show()
            } else if (phoneNumEditText.text.isEmpty()) {
                Toast.makeText(this, "전화 번호는 필수 값 입니다.", Toast.LENGTH_LONG).show()
            } else {
                val contact = createContact()
                intent.putExtra("CONTACT_RESULT", contact)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    /**
     * Contact 객체를 만드는 함수
     *
     * @return 입력 폼의 정보를 바탕으로 한 Contact 객체
     */
    private fun createContact(): Contact {
        return Contact(
            nameEditText.text.toString(),
            phoneNumEditText.text.toString(),
            emailEditText.text.toString(),
            birthdayTextView.text.toString(),
            gender,
            memoEditText.text.toString()
        )
    }

    /**
     * 더보기 layout에 대한 클릭 리스너를 설정하는 함수
     *
     * layout을 누르면 더보기 layout은 감추고 추가 입력 폼을 보이게 함.
     */
    private fun setOnClickListenerOfSeeMoreLayout() {
        seeMoreLayout.setOnClickListener {
            seeMoreLayout.visibility = View.GONE
            birthdayTextView.visibility = View.VISIBLE
            genderLayout.visibility = View.VISIBLE
            memoEditText.visibility = View.VISIBLE
        }
    }

    /**
     * genderRadioGroup에 대한 클릭 리스너를 설정하는 함수
     *
     * 선택한 성별이 여성이면 1을, 남성이면 2를 gender에 저장.
     */
    private fun setOnClickListenerOfGenderRadioGroup() {
        genderRadioGroup.setOnCheckedChangeListener { group, checkId ->
            gender = when (checkId) {
                R.id.woman_radio_btn -> 1
                R.id.man_radio_btn -> 2
                else -> 0
            }
        }
    }

    /**
     * 생일 입력폼에 대한 클릭 리스너를 설정하는 함수
     *
     * 클릭 시 캘린더 dialog가 나오고 선택한 날짜가 입력폼에 입력됨.
     */
    private fun setOnClickListenerOfBirthdayEditText() {
        birthdayTextView.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val formattedDate = "${year}.${month}.${dayOfMonth}"
                birthdayTextView.text = formattedDate
            }
            DatePickerDialog(this, dateListener, year, month, day).show()
        }
    }
}

