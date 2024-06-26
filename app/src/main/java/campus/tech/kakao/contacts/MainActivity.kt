package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    fun startCalenderDialog(textView : TextView){
        val datePickerDialog = DatePickerDialog(this)
        datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
            textView.text = getString(R.string.birthday,year,month+1,dayOfMonth)
        }
        datePickerDialog.show()
    }

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
        val showDetail = findViewById<LinearLayout>(R.id.more)
        val bday = findViewById<TextView>(R.id.contactBirthDay)

        bday.setOnClickListener{
            startCalenderDialog(it as TextView)
        }

        showDetail.setOnClickListener {
            extendEditTextList(editTextList,R.dimen.contact_list_height_detail)
            toggleViewVisibility(showDetail)
        }
    }
}
