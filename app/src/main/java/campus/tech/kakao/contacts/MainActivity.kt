package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.detail_button).setOnClickListener{
            it.visibility = View.GONE
            findViewById<LinearLayout>(R.id.detail_form_area).visibility = View.VISIBLE
        }
    }
}
