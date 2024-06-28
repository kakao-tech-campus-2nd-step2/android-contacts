package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

    class PlusActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.plus_screen)

            var PlusButton : ImageButton = findViewById(R.id.PlusButton)

            val intent = Intent(this,MainActivity::class.java)
            PlusButton.setOnClickListener{
                startActivity(intent)
            }

        }
    }