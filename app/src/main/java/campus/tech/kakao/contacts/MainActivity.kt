package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcontact)
        setMoreOptionsListener()
        setButtonsListener()
    }

    fun setMoreOptionsListener(){
        findViewById<View>(R.id.more_options).setOnClickListener {
            appendOptions()
        }
    }

    fun setButtonsListener(){
        findViewById<Button>(R.id.button_submit).setOnClickListener {
            saveSuccess()
        }
        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            cancel()
        }
    }

    fun appendOptions(){
        findViewById<View>(R.id.more_options).visibility = View.GONE
        findViewById<View>(R.id.additional_inputs).visibility = View.VISIBLE
    }

    fun saveSuccess(){
        showToast("저장이 완료 되었습니다")
    }

    fun cancel(){
        showToast("취소 되었습니다")
    }

    fun showToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
