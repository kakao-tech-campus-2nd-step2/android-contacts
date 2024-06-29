package campus.tech.kakao.contacts


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import campus.tech.kakao.contacts.database.Contact
import campus.tech.kakao.contacts.viewmodel.ContactViewModel


class AddContactActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var birthdayEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var memoEditText: EditText
    private lateinit var cancelButton: TextView
    private lateinit var saveButton: TextView
    private lateinit var moreButton: LinearLayoutCompat
    private lateinit var moreFields: LinearLayoutCompat
    private lateinit var phoneNumber: PhoneNumber

    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setClickListeners()
    }

    private fun initViews() {
        nameEditText = findViewById(R.id.nameEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        emailEditText = findViewById(R.id.emailEditText)
        birthdayEditText = findViewById(R.id.birthdayEditText)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        memoEditText = findViewById(R.id.memoEditText)
        cancelButton = findViewById(R.id.cancelButton)
        saveButton = findViewById(R.id.saveButton)
        moreButton = findViewById(R.id.moreButton)
        moreFields = findViewById(R.id.moreFields)
        phoneNumber = PhoneNumber(phoneEditText)
    }

    private fun setClickListeners() {
        cancelButton.setOnClickListener { onCancelClicked() }
        saveButton.setOnClickListener { onSaveClicked() }
        moreButton.setOnClickListener { onMoreClicked() }
        birthdayEditText.setOnClickListener { onBirthdayClicked() }
    }

    private fun onCancelClicked() {
        showToast("취소합니다.")
        onBackPressed()
    }

    override fun onBackPressed(){
        AlertDialog.Builder(this)
            .setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
            .setPositiveButton("나가기") { _, _ ->
                resetForm()
                super.onBackPressed()
            }
            .setNegativeButton("작성하기", null)
            .show()
    }

    private fun onSaveClicked() {
        if (validateInputs()) {
            showToast("저장합니다.")
            try {
                val contact = createContactFromInput()
                contactViewModel.insert(contact)
                Log.d("testt", "User: data is inserted")
                resetForm()
                val intent = Intent(this, ContactListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                Log.e("testt", "Error inserting user", e)
            }
        }
    }

    private fun onMoreClicked() {
        moreFields.visibility = LinearLayoutCompat.VISIBLE
        moreButton.visibility = LinearLayoutCompat.GONE
    }

    private fun onBirthdayClicked() {
        BirthdayPickerDialog(this, birthdayEditText).show()
    }

    private fun createContactFromInput(): Contact {
        return Contact(
            name = nameEditText.text.toString(),
            phone = phoneEditText.text.toString(),
            email = emailEditText.text.toString(),
            birthday = birthdayEditText.text.toString(),
            gender = getSelectedGender(),
            memo = memoEditText.text.toString()
        )
    }

    private fun getSelectedGender(): String? {
        return when (genderRadioGroup.checkedRadioButtonId) {
            R.id.femaleRadioButton -> "여"
            R.id.maleRadioButton -> "남"
            else -> null
        }
    }

    private val requiredFields = listOf(
        Pair("이름") { nameEditText.text.isNullOrEmpty() },
        Pair("전화번호") { phoneEditText.text.isNullOrEmpty() }
    )

    private fun validateInputs(): Boolean {
        val missingFields = requiredFields.filter { it.second() }.map { it.first }

        return when {
            missingFields.isNotEmpty() -> {
                showToast("${missingFields.joinToString(", ")}을(를) 입력하세요.")
                false
            }
            !phoneNumber.isValidPhoneNumber() -> {
                showToast("전화번호 형식이 올바르지 않습니다.")
                false
            }
            else -> true
        }
    }

    private fun resetForm() {
        nameEditText.text.clear()
        phoneEditText.text.clear()
        emailEditText.text.clear()
        birthdayEditText.text.clear()
        genderRadioGroup.clearCheck()
        memoEditText.text.clear()
        moreFields.visibility = LinearLayoutCompat.GONE
        moreButton.visibility = LinearLayoutCompat.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}


