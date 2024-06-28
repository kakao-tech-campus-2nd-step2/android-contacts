package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var contactAddBtn: FloatingActionButton = findViewById(R.id.contactAddBtn)

        val startActivityLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                when (it.resultCode) {
                    RESULT_OK -> {
                        Log.d("testNameData", it.data?.extras?.getString("name")!!)
                    }
                }
            }
        contactAddBtn.apply {
            this.setOnClickListener {
                val intent = Intent(this@MainActivity, ContactAdd::class.java)
                startActivityLauncher.launch(intent)
            }
        }
    }
}
