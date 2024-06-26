package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    lateinit var registerBtnLayout:FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        setOnClickListeners()
    }

    private fun initViews() {
        registerBtnLayout = findViewById(R.id.register_btn_layout)
    }

    private fun setOnClickListeners() {
        setOnClickListenerOfRegisterBtnLayout()
    }

    private fun setOnClickListenerOfRegisterBtnLayout() {
        registerBtnLayout.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
        }
    }

}