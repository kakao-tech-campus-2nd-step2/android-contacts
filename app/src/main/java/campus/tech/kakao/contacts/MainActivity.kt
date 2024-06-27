package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityResultLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                when (it.resultCode) {
                    RESULT_OK -> {
                        Log.d("Main","Success")
                    }
                    RESULT_CANCELED -> {
                        Log.d("Main","Canceled")
                    }
                }
            }
        val addButton: FloatingActionButton = findViewById(R.id.add_contact)
        addButton.setOnClickListener {
            val contactIntent: Intent = Intent(this, ContactActivity::class.java)
            activityResultLauncher.launch(contactIntent)
        }
    }
}
