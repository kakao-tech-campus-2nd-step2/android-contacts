package campus.tech.kakao.contacts

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cancelButtonSetOnClickListner(this)
    }

    fun cancelButtonSetOnClickListner(context : Context){
        findViewById<TextView>(R.id.button_cancel).setOnClickListener(){
            displayCancelMessage(context)
        }
    }

    fun displayCancelMessage(context: Context){
        val cancelMessage = Toast.makeText(context, "취소 되었습니다", Toast.LENGTH_SHORT)
        cancelMessage.show()
    }

}

