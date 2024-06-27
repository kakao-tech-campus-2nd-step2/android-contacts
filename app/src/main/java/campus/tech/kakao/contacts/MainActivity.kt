package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import org.w3c.dom.Text
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var NameEdit : EditText = findViewById(R.id.NameEdit)
        var PhoneNumberEdit : EditText = findViewById(R.id.PhoneNumberEdit)
        var MoreText : TextView = findViewById(R.id.MoreText)
        var BirthEdit : EditText = findViewById(R.id.BirthEdit)
        var MemoEdit : EditText = findViewById(R.id.MemoEdit)
        var GenderArea : LinearLayoutCompat = findViewById(R.id.GenderArea)
        var SaveText : TextView = findViewById(R.id.SaveText)
        var CancleText : TextView = findViewById(R.id.CancleText)

         MoreText.setOnClickListener {
            MoreText.visibility = View.GONE
            BirthEdit.visibility = View.VISIBLE
            MemoEdit.visibility = View.VISIBLE
            GenderArea.visibility = View.VISIBLE
        }


        SaveText.setOnClickListener {
            if (NameEdit.text.toString().trim().isEmpty()){
                Toast
                    .makeText(this@MainActivity,"이름은 필수값입니다.",Toast.LENGTH_SHORT)
                    .show()
            }
            if (PhoneNumberEdit.text.toString().trim().isEmpty()){
                Toast
                    .makeText(this@MainActivity,"전화번호는 필수값입니다.",Toast.LENGTH_SHORT)
                    .show()
            }

            else
            {
                Toast
                    .makeText(this@MainActivity,"저장되었습니다.",Toast.LENGTH_SHORT)
                    .show()
            }
        }

        CancleText.setOnClickListener{
            Toast
                .makeText(this@MainActivity,"취소되었습니다.",Toast.LENGTH_SHORT)
                .show()
        }
    }
}
