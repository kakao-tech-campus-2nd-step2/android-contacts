package campus.tech.kakao.contacts

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserInfoActivity : AppCompatActivity() {
    private lateinit var name: TextView
    private lateinit var phoneNumber: TextView
    private lateinit var email: TextView
    private lateinit var birthDay: TextView
    private lateinit var gender: TextView
    private lateinit var memo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

    }

    private fun init(){
        setViews()
        receiveData()
    }

    private fun setViews(){
        name = findViewById<TextView>(R.id.name)
        phoneNumber = findViewById<TextView>(R.id.phoneNumber)
        email = findViewById<TextView>(R.id.email)
        birthDay = findViewById<TextView>(R.id.birthDay)
        gender = findViewById<TextView>(R.id.gender)
        memo = findViewById<TextView>(R.id.memo)
    }

    private fun receiveData(){
        val userData = intent.getSerializableExtra("userData")

        if (userData is UserData) {
            displayUserData(userData)
        }
    }

    private fun displayUserData(userData: UserData){
        name.text = userData.name
        phoneNumber.text = userData.phoneNumber.toString()
        email.text = userData.email
        birthDay.text = userData.birthday
        gender.text = userData.gender
        memo.text = userData.memo
    }

}