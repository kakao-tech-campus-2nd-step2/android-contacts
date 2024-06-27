package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
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
                            val resContact =
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                                    intent.getSerializableExtra("res",Contact::class.java)
                                else
                                    it.data?.extras?.getSerializable("res") as Contact?
                        Log.d("MainActivity", resContact.toString() )
                        Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
                    }
                    RESULT_CANCELED -> {
                        Log.d("Main","Canceled")
                        Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
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
