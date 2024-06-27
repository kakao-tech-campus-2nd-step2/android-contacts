    package campus.tech.kakao.contacts

    import android.os.Bundle
    import android.util.Log
    import android.view.View
    import android.widget.EditText
    import android.widget.RadioGroup
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity
    import androidx.appcompat.widget.LinearLayoutCompat

    class MainActivity : AppCompatActivity() {

        val contactManager = ContactManager()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

        }
    }
