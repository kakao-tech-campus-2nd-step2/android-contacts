package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var phoneNumEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var birthdayTextView: TextView
    private lateinit var genderLayout: ConstraintLayout
    private lateinit var memoEditText: EditText
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var seeMoreLayout: LinearLayoutCompat
    private lateinit var genderRadioGroup: RadioGroup
    private var gender: Gender? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setOnClickListeners()
        registerOnBackPressedCallback()
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
     * 취소 버튼에 대한 클릭 리스너를 설정하는 함수.
     *
     * 취소 버튼을 누르면 확인 팝업 보여줌.
     */
    private fun setOnClickListenerOfCancelBtn() {
        cancelBtn.setOnClickListener {
            showAlertDialog()
        }
    }

    /**
     * 저장 버튼에 대한 클릭 리스너를 설정하는 함수
     *
     * 이름 또는 전화번호 입력 폼이 비어있을 경우 Toast 메시지 출력.
     * 아닌 경우 입력한 정보를 contact 객체로 담아 결과를 보내고 finish.
     *
     * - `contact` : 입력한 연락처 정보를 담고 있는 Contact 객체
     * - `name` : 작성한 이름 String
     * - `phoneNum` : 작성한 전화 번호 String
     */
    private fun setOnClickListenerOfSaveBtn() {
        saveBtn.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val phoneNum = phoneNumEditText.text.toString().trim()

            when {
                name.isEmpty() -> {
                    Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_LONG).show()
                }
                phoneNum.isEmpty() -> {
                    Toast.makeText(this, "전화 번호는 필수 값 입니다.", Toast.LENGTH_LONG).show()
                }
                !phoneNum.matches(Regex("^\\d{10,11}\$")) -> {
                    Toast.makeText(this, "전화 번호는 10자리 또는 11자리의 숫자만 입력 가능합니다.", Toast.LENGTH_LONG).show()
                }
                else -> {
                    val contact = createContact()
                    intent.putExtra("CONTACT_RESULT", contact)
                    setResult(RESULT_OK, intent)
                    finish()
                }
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
            memoEditText.text.toString(),
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
            gender =
                when (checkId) {
                    R.id.woman_radio_btn -> Gender.FEMALE
                    R.id.man_radio_btn -> Gender.MALE
                    else -> null
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
            val dateListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val formattedDate = "$year.$month.$dayOfMonth"
                    birthdayTextView.text = formattedDate
                }
            DatePickerDialog(this, dateListener, year, month, day).show()
        }
    }

    /**
     * 뒤로 가기 버튼을 누르면 확인 팝업을 보여주도록 설정하는 함수
     *
     */
    private fun registerOnBackPressedCallback() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    showAlertDialog()
                }
            },
        )
    }

    /**
     * 작성 내용 취소 / 뒤로가기 시 확인 팝업을 보여주는 함수.
     *
     * 나가기를 누르면 액티비티가 종료되고 작성하기를 누르면 다시 작성 가능함.
     */
    private fun showAlertDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
            setPositiveButton("나가기") { _, _ ->
                finish()
            }
            setNegativeButton("작성하기", null)
            create()
            show()
        }
    }
}
