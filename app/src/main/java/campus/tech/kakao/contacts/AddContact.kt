package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class AddContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

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
            val isCorrectInput = RequiredInputVerify()
            if (isCorrectInput) {
                clickSaveEvent()
            }
        }
    }

    fun swapVisibility(viewA: View, viewB: View) {
        val temp = viewA.visibility
        viewA.visibility = viewB.visibility
        viewB.visibility = temp
    }

    fun clickCancelEvent() {
        val output = "취소 되었습니다"
        Toast.makeText(this, output, Toast.LENGTH_SHORT).show()
        finish()
    }

    fun clickSaveEvent() {
        val name = findViewById<EditText>(R.id.name)
        val phoneNumber = findViewById<EditText>(R.id.phone_number)

        val intent = Intent()
        intent.putExtra("name", name.text.toString())
        intent.putExtra("phoneNumber", phoneNumber.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }

    fun RequiredInputVerify(): Boolean {
        var output = "저장이 완료 되었습니다"
        var isCorrectInput = true

        val phoneNumber = findViewById<EditText>(R.id.phone_number)
        if (phoneNumber.text.toString() == "") {
            output = "전화번호는 필수 값입니다"
            isCorrectInput = false
        }

        val name = findViewById<EditText>(R.id.name)
        if (name.text.toString() == "") {
            output = "이름은 필수 값입니다"
            isCorrectInput = false
        }

        Toast.makeText(this, output, Toast.LENGTH_SHORT).show()
        return isCorrectInput
    }
}