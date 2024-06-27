package campus.tech.kakao.contacts

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var more: Button
    private lateinit var extraFieldsLayout: LinearLayout
    private lateinit var birthday: EditText
    private lateinit var genderGroup: RadioGroup
    private lateinit var female: RadioButton
    private lateinit var male: RadioButton
    private lateinit var memo: EditText
    private lateinit var save: Button
    private lateinit var cancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.name)
        phone = findViewById(R.id.phoneNumber)
        email = findViewById(R.id.emailAddress)
        more = findViewById(R.id.more)
        extraFieldsLayout = findViewById(R.id.extra_fields)
        birthday = findViewById(R.id.birthday)
        genderGroup = findViewById(R.id.genderGroup)
        female = findViewById(R.id.female)
        male = findViewById(R.id.male)
        memo = findViewById(R.id.memo)
        save = findViewById(R.id.save)
        cancel = findViewById(R.id.cancel)


        save.setOnClickListener {
            if(name.text.toString().isEmpty()) {
                Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(phone.text.toString().isEmpty()) {
                Toast.makeText(this, "전화번호는 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "저장이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
