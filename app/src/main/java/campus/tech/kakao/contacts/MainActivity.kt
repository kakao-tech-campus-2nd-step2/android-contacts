package campus.tech.kakao.contacts

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val name = findViewById<EditText>(R.id.name)
        val phone = findViewById<EditText>(R.id.phone)
        val save = findViewById<TextView>(R.id.save)
        val cancel = findViewById<TextView>(R.id.cancel)

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        save.setOnClickListener{
            if(name.text.isEmpty()) {
                val toast = Toast.makeText(this, "이름은 필수값입니다", Toast.LENGTH_SHORT)
                toast.show()
                name.requestFocus()
                inputMethodManager.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT)
            } else if(phone.text.isEmpty()) {
                val toast = Toast.makeText(this, "전화번호는 필수값입니다", Toast.LENGTH_SHORT)
                toast.show()
                phone.requestFocus()
                inputMethodManager.showSoftInput(phone, InputMethodManager.SHOW_IMPLICIT)
            } else {
                val toast = Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        cancel.setOnClickListener{
            val toast = Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT)
            toast.show()
        }


    }
}
