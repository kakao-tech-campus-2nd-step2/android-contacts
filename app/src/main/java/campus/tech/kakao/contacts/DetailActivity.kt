package campus.tech.kakao.contacts

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class DetailActivity : AppCompatActivity() {
    lateinit var detailNameTextView: TextView
    lateinit var detailPhoneNumTextView: TextView
    lateinit var detailEmailTextView: TextView
    lateinit var detailBirthdayTextView: TextView
    lateinit var detailGenderTextView: TextView
    lateinit var detailMemoTextView: TextView
    lateinit var detailEmailLayout: ConstraintLayout
    lateinit var detailBirthdayLayout: ConstraintLayout
    lateinit var detailGenderLayout: ConstraintLayout
    lateinit var detailMemoLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initViews()
        setContactDetails()
    }

    /**
     * 사용할 view들을 초기화하는 함수
     *
     * - `detailNameTextView` : 이름을 나타내는 TextView
     * - `detailPhoneNumTextView` : 전화번호를 나타내는 TextView
     * - `detailEmailTextView` : 메일을 나타내는 TextView
     * - `detailBirthdayTextView` : 생일을 나타내는 TextView
     * - `detailGenderTextView` : 성별을 나타내는 TextView
     * - `detailMemoTextView` : 메모를 나타내는 TextView
     * - `detailEmailLayout` : 메일 ConstraintLayout
     * - `detailBirthdayLayout` : 생일 ConstraintLayout
     * - `detailGenderLayout` : 성별 ConstraintLayout
     * - `detailMemoLayout` : 메모 ConstraintLayout
     */
    private fun initViews() {
        detailNameTextView = findViewById(R.id.detail_name_text_view)
        detailPhoneNumTextView = findViewById(R.id.detail_phone_num_text_view)
        detailEmailTextView = findViewById(R.id.detail_email_text_view)
        detailBirthdayTextView = findViewById(R.id.detail_birthday_text_view)
        detailGenderTextView = findViewById(R.id.detail_gender_text_view)
        detailMemoTextView = findViewById(R.id.detail_memo_text_view)

        detailEmailLayout = findViewById(R.id.detail_email_layout)
        detailBirthdayLayout = findViewById(R.id.detail_birthday_layout)
        detailGenderLayout = findViewById(R.id.detail_gender_layout)
        detailMemoLayout = findViewById(R.id.detail_memo_layout)
    }

    /**
     * 전달된 contact 정보를 view와 layout에 설정하는 함수.
     */
    private fun setContactDetails() {
        val contact: Contact? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("contact", Contact::class.java)
            } else {
                intent.getParcelableExtra("contact")
            }

        contact?.let {
            setDetailTextViews(it)
            setDetailLayoutVisibility(it)
        }
    }

    /**
     * contact 정보로 TextView의 text를 설정하는 함수.
     *
     * @param contact RegisterActivity로 부터 받은 Contact 객체.
     */
    private fun setDetailTextViews(contact: Contact) {
        detailNameTextView.text = contact.name
        detailPhoneNumTextView.text = contact.phoneNum
        detailEmailTextView.text = contact.email ?: ""
        detailBirthdayTextView.text = contact.birthday ?: ""
        detailGenderTextView.text =
            when (contact.gender) {
                1 -> "여성"
                2 -> "남성"
                else -> ""
            }
        detailMemoTextView.text = contact.memo ?: ""
    }

    /**
     * contact 정보로 layout의 visibility를 설정하는 함수.
     *
     * 빈 정보는 보이지 않도록 설정.
     *
     * @param contact RegisterActivity로 부터 받은 Contact 객체.
     */
    private fun setDetailLayoutVisibility(contact: Contact) {
        detailEmailLayout.isVisible = !contact.email.isEmpty()
        detailBirthdayLayout.isVisible = !contact.birthday.isEmpty()
        detailGenderLayout.isVisible = contact.gender != 0
        detailMemoLayout.isVisible = !contact.memo.isEmpty()
    }
}
