package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moreBtn = findViewById<TextView>(R.id.more)
        val moreForm = findViewById<ConstraintLayout>(R.id.more_form)
        val cancel = findViewById<TextView>(R.id.cancel)
        val save = findViewById<TextView>(R.id.save)

        moreBtn.setOnClickListener {
            swapVisibility(moreBtn, moreForm)
        }
        cancel.setOnClickListener {
            clickCancelEvent()
        }
        save.setOnClickListener {
            RequiredInputVerify()
        }
    }

    fun swapVisibility(viewA: View, viewB: View) {
        val temp = viewA.visibility
        viewA.visibility = viewB.visibility
        viewB.visibility = temp
    }

    fun clickCancelEvent() {
        val output = "취소 되었습니다"
        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show()
    }

    fun RequiredInputVerify() {
        var output = "저장이 완료 되었습니다"

        val phoneNumber = findViewById<EditText>(R.id.phone_number)
        if (phoneNumber.text.toString() == "")
            output = "전화번호는 필수 값입니다"

        val name = findViewById<EditText>(R.id.name)
        if (name.text.toString() == "")
            output = "이름은 필수 값입니다"

        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show()
    }
}
