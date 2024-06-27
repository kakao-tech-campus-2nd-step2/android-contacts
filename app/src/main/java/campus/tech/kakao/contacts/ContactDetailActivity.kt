package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_detail)

        //view 초기화 보는 거
        val profileView = findViewById<ImageView>(R.id.profileView)
        val nameView = findViewById<TextView>(R.id.nameView)
        val phoneView = findViewById<TextView>(R.id.phoneView)
        val emailView = findViewById<TextView>(R.id.emailView)
        val birthView = findViewById<TextView>(R.id.birthView)
        val genderView = findViewById<TextView>(R.id.genderView)
        val memoView = findViewById<TextView>(R.id.memoView)

        val nameLabelText = findViewById<TextView>(R.id.nameLabelText)
        val phoneLabelText = findViewById<TextView>(R.id.phoneLabelText)
        val emailLabelText = findViewById<TextView>(R.id.emailLabelText)
        val birthLabelText = findViewById<TextView>(R.id.birthLabelText)
        val genderLabelText = findViewById<TextView>(R.id.genderLabelText)
        val memoLabelText = findViewById<TextView>(R.id.memoLabelText)

        //parcel에 적어서 intent로 전달된 contact 객체 가져오기
        val contact = intent.getParcelableExtra<Contact>("contact")

        //연락처 정보 설정하기 - 조건
        contact?.let {

            //이름 필수조건
            nameView.text = it.name
            nameView.visibility = TextView.VISIBLE
            nameLabelText.visibility = TextView.VISIBLE

            //이메일 필수조건
            phoneView.text = it.phone
            phoneView.visibility = TextView.VISIBLE
            phoneLabelText.visibility = TextView.VISIBLE

            //email
            if (!it.email.isNullOrEmpty()) {
                emailView.text = it.email
                emailView.visibility = TextView.VISIBLE
                emailLabelText.visibility = TextView.VISIBLE
            } else {
                emailView.visibility = TextView.GONE
                emailLabelText.visibility = TextView.GONE
            }

            //birth
            if (!it.birth.isNullOrEmpty()) {
                birthView.text = it.birth
                birthLabelText.visibility = TextView.VISIBLE
            } else {
                birthView.visibility = TextView.GONE
                birthLabelText.visibility = TextView.GONE
            }

            //gender
            //default 값이 아닌 경우 값을 집어 넣도록 설정 - '성별' 문구 안뜨도록
            if (!it.gender.isNullOrEmpty() && it.gender != " ") {
                genderView.text = it.gender
                genderView.visibility = TextView.VISIBLE
                genderLabelText.visibility = TextView.VISIBLE
            } else {
                genderView.visibility = TextView.GONE
                genderLabelText.visibility = TextView.GONE
            }

            //memo
            if (!it.memo.isNullOrEmpty()) {
                memoView.text = it.memo
                memoView.visibility = TextView.VISIBLE
                memoLabelText.visibility = TextView.VISIBLE
            } else {
                memoView.visibility = TextView.GONE
                memoLabelText.visibility = TextView.GONE
            }
        }
    }
}