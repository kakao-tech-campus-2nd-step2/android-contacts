package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<TextInputLayout>(R.id.name)
        val phoneNumber = findViewById<TextInputLayout>(R.id.phoneNumber)
        val email = findViewById<TextInputLayout>(R.id.email)
        val birthDay = findViewById<TextInputLayout>(R.id.birthDay)
        val gender = findViewById<RadioGroup>(R.id.gender)
        val memo = findViewById<TextInputLayout>(R.id.memo)

        val birthDayLayout = findViewById<LinearLayoutCompat>(R.id.birthDayLayout)
        val genderLayout = findViewById<LinearLayoutCompat>(R.id.genderLayout)
        val memoLayout = findViewById<LinearLayoutCompat>(R.id.memoLayout)

        val more = findViewById<LinearLayoutCompat>(R.id.more)
        val save = findViewById<TextView>(R.id.save)
        val cancle = findViewById<TextView>(R.id.cancle)

        more.setOnClickListener {
            birthDayLayout.visibility = View.VISIBLE
            genderLayout.visibility = View.VISIBLE
            memoLayout.visibility = View.VISIBLE
            more.visibility = View.GONE
        }

        cancle.setOnClickListener {

        }

        save.setOnClickListener {

        }
    }
}
