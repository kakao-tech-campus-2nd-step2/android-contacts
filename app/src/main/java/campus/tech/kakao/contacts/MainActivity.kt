package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextList = findViewById<LinearLayout>(R.id.editTextList)
        val showMore = findViewById<LinearLayout>(R.id.more)

        showMore.setOnClickListener {
            var layoutParams = editTextList.layoutParams
            layoutParams.height = R.dimen.contact_list_height_detail
            editTextList.layoutParams = layoutParams
            showMore.visibility = INVISIBLE
        }
    }
}
