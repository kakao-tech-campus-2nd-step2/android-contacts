package campus.tech.kakao.contacts

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.regex.Pattern

class PhoneNumber(private val editText: EditText) {

    init {
        editText.addTextChangedListener(object : TextWatcher{
            private var current = ""
            private val phonePattern = "###-####-####"
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                formatPhoneNumber(editable)
            }

            private fun formatPhoneNumber(editable: Editable?) {
                if (editable.toString() != current) {
                    val userInput = editable.toString().replace(Regex("[^\\d]"), "")
                    val sb = StringBuilder()
                    var i = 0
                    for (c in phonePattern.toCharArray()) {
                        if (c != '#' && userInput.length > i) {
                            sb.append(c)
                        } else if (userInput.length > i) {
                            sb.append(userInput[i])
                            i++
                        }
                    }
                    current = sb.toString()
                    editText.removeTextChangedListener(this)
                    editText.setText(current)
                    editText.setSelection(current.length)
                    editText.addTextChangedListener(this)
                }
            }
        })
    }

    fun isValidPhoneNumber(): Boolean {
        val phonePattern = Pattern.compile("^(01[0-9]-[0-9]{3,4}-[0-9]{4}|\\+82-[0-9]{2,3}-[0-9]{3,4}-[0-9]{4})$")
        return phonePattern.matcher(editText.text.toString()).matches()
    }
}