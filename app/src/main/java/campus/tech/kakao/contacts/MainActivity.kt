package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val contacts = mutableListOf<MutableList<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gotoAddContactBtn = findViewById<Button>(R.id.goto_add_contact)

        gotoAddContactBtn.setOnClickListener {
            gotoAddContact()
        }
    }

    }

    }




    }
}