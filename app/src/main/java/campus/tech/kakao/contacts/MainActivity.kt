package campus.tech.kakao.contacts

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Configuration
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: PersonAdapter
    lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var emptyTextview: TextView
    var personList: MutableList<Person> = mutableListOf<Person>()

    lateinit var registContactLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = PersonAdapter(personList) {
            val intent = Intent(this, ViewContact::class.java).apply {
                putExtra("name", it.name)
                putExtra("tel", it.tel)
                putExtra("mail", it.mail)
                putExtra("sex", it.sex)
                putExtra("memo", it.memo)
                putExtra("birth", it.birth)
            }
            startActivity(intent)
        }

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        emptyTextview = findViewById(R.id.empty_textview)

        registContactLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val name = data?.getStringExtra("name") ?: ""
                val tel = data?.getStringExtra("tel") ?: ""
                val mail = data?.getStringExtra("mail") ?: ""
                val sex = data?.getStringExtra("sex") ?: ""
                val memo = data?.getStringExtra("memo") ?: ""
                val birth = data?.getStringExtra("birth") ?: ""

                personList.add(Person(name, tel, mail, sex, memo, birth))
                viewAdapter.notifyDataSetChanged()
                updateUI()
            }
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            val intent = Intent(this, ResiterContact::class.java)
            registContactLauncher.launch(intent)
        }

        updateUI()
    }

    private fun updateUI() {
        if (personList.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyTextview.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyTextview.visibility = View.GONE
        }
    }
}
