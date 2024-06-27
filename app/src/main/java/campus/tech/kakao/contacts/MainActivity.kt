package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val seeMoreButton = findViewById<Button>(R.id.seeMoreButton)
        seeMore(seeMoreButton)
    }
    private fun seeMore(seeMoreButton:Button){
        seeMoreButton.setOnClickListener {
            val birthdayEdit = findViewById<EditText>(R.id.birthdayEdit)
            val genderEdit = findViewById<EditText>(R.id.genderEdit)
            val memoEdit = findViewById<EditText>(R.id.memoEdit)
            val genderRadio = findViewById<RadioGroup>(R.id.genderRadio)
            birthdayEdit.visibility = EditText.VISIBLE
            genderEdit.visibility = EditText.VISIBLE
            memoEdit.visibility = EditText.VISIBLE
            genderRadio.visibility = RadioGroup.VISIBLE
        }

    }
}
