package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

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
            nameView.text = it.name //필수
            phoneView.text = it.phone //필수

            //email
            if (!it.email.isNullOrEmpty()) {
                emailView.text = it.email
            } else {
                emailView.visibility = TextView.GONE
                emailLabelText.visibility = TextView.GONE
            }

            //birth
            if (!it.birth.isNullOrEmpty()) {
                birthView.text = it.birth
            } else {
                birthView.visibility = TextView.GONE
                birthLabelText.visibility = TextView.GONE
            }

            //gender
            if (!it.gender.isNullOrEmpty()) {
                genderView.text = it.gender
            } else {
                genderView.visibility = TextView.GONE
                genderLabelText.visibility = TextView.GONE
            }

            //memo
            if (!it.memo.isNullOrEmpty()) {
                memoView.text = it.memo
            } else {
                memoView.visibility = TextView.GONE
                memoLabelText.visibility = TextView.GONE
            }
        }
    }
}