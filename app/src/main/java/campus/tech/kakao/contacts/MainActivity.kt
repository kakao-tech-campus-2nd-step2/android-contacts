package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var tel: EditText
    private lateinit var mail: EditText
    private lateinit var birth: EditText
    private lateinit var gender: EditText
    private lateinit var genderRadio: LinearLayout
    private lateinit var memo: EditText
    private lateinit var viewMore: TextView
    private lateinit var cancel: Button
    private lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()
    }

    private fun initializeView() {
        name = findViewById(R.id.name)
        tel = findViewById(R.id.tel)
        mail = findViewById(R.id.mail)
        birth = findViewById(R.id.birth)
        gender = findViewById(R.id.gender)
        genderRadio = findViewById(R.id.genderRadio)
        memo = findViewById(R.id.memo)
        viewMore = findViewById(R.id.viewMore)
        cancel = findViewById(R.id.cancel)
        save = findViewById(R.id.save)
    }



}
