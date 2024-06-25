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
        val save = findViewById<TextView>(R.id.save)

        save.setOnClickListener{
            if(name.text.isEmpty()) {
                val toast = Toast.makeText(this, "이름은 필수값입니다", Toast.LENGTH_SHORT)
                toast.show()
                name.requestFocus()
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }
}
