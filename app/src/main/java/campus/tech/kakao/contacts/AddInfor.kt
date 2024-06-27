package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddInfor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_infor)
        val forMoreInformation = findViewById<Button>(R.id.forMoreInformation)
        val addInfor = findViewById<LinearLayout>(R.id.addInfor)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val inputName = findViewById<EditText>(R.id.inputName)
        val inputPhoneNumber = findViewById<EditText>(R.id.inputPhoneNumber)
    }
}
private fun saveMessage(saveButton: Button) {
    Toast.makeText(saveButton.context, "저장이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
}
private fun emptyDatamassage(saveButton: Button) {
    Toast.makeText(saveButton.context, "이름과 전화번호는 필수 값입니다.", Toast.LENGTH_SHORT).show()
}
private fun cancelMessasge(cancelButton: Button) {
    Toast.makeText(cancelButton.context, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
}