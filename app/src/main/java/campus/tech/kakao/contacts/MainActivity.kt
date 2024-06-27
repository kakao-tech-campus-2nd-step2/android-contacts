package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

	private lateinit var addButton: FloatingActionButton

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		addButton = findViewById(R.id.addButton)
		addButton.setOnClickListener {
			startActivity(
				Intent(this, AddContact::class.java)
			)
		}
	}
}