package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactRegisterActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_contact_register)

		setMoreInfoVisibility()
	}

	private fun setMoreInfoVisibility() {
		findViewById<LinearLayout>(R.id.moreInfoButton).setOnClickListener { moreInfoButton ->
			findViewById<LinearLayout>(R.id.moreInfoLayout).visibility = View.VISIBLE
			moreInfoButton.visibility = View.GONE
		}
	}
}