package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    fun extendEditTextList(view : View, sizeId : Int){
        var layoutParams = view.layoutParams
        layoutParams.height = resources.getDimensionPixelSize(sizeId)
        view.layoutParams = layoutParams
    }

    fun toggleViewVisibility(view : View){
        view.visibility = when(view.visibility){
            GONE -> VISIBLE
            else -> GONE
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextList = findViewById<LinearLayout>(R.id.editTextList)
        val showMore = findViewById<LinearLayout>(R.id.more)

        showDetail.setOnClickListener {
            extendEditTextList(editTextList,R.dimen.contact_list_height_detail)
            toggleViewVisibility(showDetail)
        }
    }
}
