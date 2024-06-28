package campus.tech.kakao.contacts

import android.app.DatePickerDialog
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
import java.util.Calendar
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.activity.OnBackPressedCallback
import android.content.Intent



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

        //더보기 버튼
        more.setOnClickListener {
            more.visibility = View.GONE
            extraFieldsLayout.visibility = View.VISIBLE
        }

        //저장 버튼
        save.setOnClickListener {
            if(name.text.toString().isEmpty()) {
                Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(phone.text.toString().isEmpty()) {
                Toast.makeText(this, "전화번호는 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val contact = Contact(
                name.text.toString(),
                phone.text.toString(),
                email.text.toString(),
                birthday.text.toString(),
                if (female.isChecked) "여성" else if (male.isChecked) "남성" else null,
                memo.text.toString()
            )


            val intent = Intent().apply {
                putExtra("CONTACT", contact)
            }
            setResult(RESULT_OK, intent)
            Toast.makeText(this, "저장이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        //취소 버튼
        cancel.setOnClickListener {
            if (isFormEmpty()) {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                showExitConfirmationDialog()
            }
        }

        //생일 입력 설정(달력 사용)
        birthday.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                birthday.setText("$selectedYear-${selectedMonth + 1}-$selectedDay")
            }, year, month, day)

            datePickerDialog.show()
        }

        //뒤로 가기 버튼: 입력 폼 작성 X -> 바로 나가기 / 입력 폼 작성 O -> 팝업 띄우기
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isFormEmpty()) {
                    finish()
                } else {
                    showExitConfirmationDialog()
                }
            }
        })

    }

    //입력 폼 작성 여부 검사
    private fun isFormEmpty(): Boolean {
        return name.text.isEmpty() && phone.text.isEmpty() && email.text.isEmpty() &&
                birthday.text.isEmpty() && genderGroup.checkedRadioButtonId == -1 && memo.text.isEmpty()
    }

    //팝업
    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
            .setPositiveButton("나가기") { _, _ -> finish() }
            .setNegativeButton("작성하기", null)
            .show()
    }

}
