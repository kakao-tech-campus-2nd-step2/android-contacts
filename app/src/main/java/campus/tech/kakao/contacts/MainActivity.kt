package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcontact)
        setMoreOptionsListener()
    }

    fun setMoreOptionsListener(){
        findViewById<View>(R.id.more_options).setOnClickListener {
            appendOptions()
        }
    }

    fun appendOptions(){
        findViewById<View>(R.id.more_options).visibility = View.GONE
        findViewById<View>(R.id.additional_inputs).visibility = View.VISIBLE
    }
}
