package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.contacts.databinding.ActivitySaveContactBinding

class SaveContactActivity : AppCompatActivity() {

    lateinit var imm : InputMethodManager
    lateinit var binding: ActivitySaveContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySaveContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        showMoreEditTexts()
        showDatePicker()
        onCancelClicked()
        onSaveClicked()
    }

    override fun onResume() {
        super.onResume()
        clearData()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearData()
    }

    fun showMoreEditTexts(){
        binding.btnMore.setOnClickListener {
            binding.btnMore.visibility = if(binding.btnMore.visibility == View.VISIBLE){
                View.GONE
            } else {View.VISIBLE}
            binding.addfield.visibility = if(binding.addfield.visibility == View.GONE){
                View.VISIBLE
            } else {View.GONE}
        }
    }

    fun showDatePicker(){
        binding.editBirth.setOnClickListener {
            val cal = Calendar.getInstance()
            cal.set(2000, Calendar.JANUARY, 1)

            imm.hideSoftInputFromWindow(
                currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )

            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val dateString= "${year}.${month+1}.${dayOfMonth}"
                binding.editBirth.setText(dateString)
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this@SaveContactActivity, message, Toast.LENGTH_SHORT).show()
    }

    fun onCancelClicked(){
        binding.btnCancel.setOnClickListener {
            showToast("취소 되었습니다")
        }
    }

    fun onSaveClicked(){
        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val phone = binding.editPhone.text.toString()

            if (name.isEmpty()){
                showToast("이름은 필수 입력 항목입니다")
                binding.editName.requestFocus()
            }
            else if (phone.isEmpty()){
                showToast("전화번호는 필수 입력 항목입니다")
                binding.editPhone.requestFocus()
            }
            else {
                saveData(name,phone)
                finish()
            }
        }
    }

    private fun saveData(name: String, phone: String){
        with(getSharedPreferences(CONTACT_INFORMATION, Context.MODE_PRIVATE).edit()) {
            putString(NAME, name)
            putString(PHONE, phone)
            putString(EMAIL, binding.editEmail.text.toString().takeIf { it.isNotEmpty() })
            putString(BIRTH, binding.editBirth.text.toString().takeIf { it.isNotEmpty() })

            val genderType = getGenderType()
            putString(GENDER_TYPE, genderType)

            putString(NOTE, binding.editNote.text.toString().takeIf { it.isNotEmpty() })
            apply()
        }
        showToast("저장이 완료 되었습니다")
    }

    private fun getGenderType(): String? {
        return if (binding.female.isChecked) "여성"
        else if (binding.male.isChecked) "남성"
        else null
    }

    private fun clearData() {
        with(getSharedPreferences(CONTACT_INFORMATION, Context.MODE_PRIVATE).edit()) {
            remove(NAME)
            remove(PHONE)
            remove(EMAIL)
            remove(BIRTH)
            remove(GENDER_TYPE)
            remove(NOTE)
            apply()
        }
    }
}
