package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.Calendar

class AddContact : AppCompatActivity() {
    // Declare UI elements
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var mail: EditText
    private lateinit var birth: TextView
    private var sex:String? = null
    private lateinit var female: RadioButton
    private lateinit var male: RadioButton
    private lateinit var memo: EditText
    private lateinit var save: TextView
    private lateinit var cancel: TextView
    private lateinit var moreText: TextView
    private lateinit var birthSexMemo: View

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE new_Contact (" +
                        "id INTEGER PRIMARY KEY NOT NULL, " +
                        "name TEXT NOT NULL, " +
                        "phone INTEGER NOT NULL, " +
                        "email TEXT, " +
                        "birth TEXT, " +
                        "sex TEXT, " +
                        "memo TEXT)"
            )
            // 데이터 복사
            database.execSQL(
                "INSERT INTO new_Contact (id, name, phone, email, birth, sex, memo) " +
                        "SELECT id, name, CAST(phone AS INTEGER), email, birth, sex, memo FROM Contact")
            // 이전 테이블 제거
            database.execSQL("DROP TABLE Contact")

            // 새 테이블 이름 바꾸기
            database.execSQL("ALTER TABLE new_Contact RENAME TO Contact")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcontact)
        // Initialize UI elements
        name = findViewById(R.id.name)
        phone = findViewById(R.id.phone)
        mail = findViewById(R.id.mail)
        birth = findViewById(R.id.birth)
        female = findViewById(R.id.female)
        male = findViewById(R.id.male)
        memo = findViewById(R.id.memo)
        save = findViewById(R.id.save)
        cancel = findViewById(R.id.cancel)
        birthSexMemo = findViewById(R.id.birthSexMemo)
        moreText = findViewById(R.id.moreText)
        birth.setOnClickListener {
            showDatePickerDialog()
        }
        moreText.setOnClickListener {
            if (moreText.visibility == View.VISIBLE) {
                moreText.visibility = View.GONE
                birthSexMemo.visibility = View.VISIBLE
            } else {
                moreText.visibility = View.VISIBLE
                birthSexMemo.visibility = View.GONE
            }
        }
        // Initialize database
        val database = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java, "database-name"
        ).addMigrations(MIGRATION_1_2).allowMainThreadQueries().build()

        // Set click listener for save button
        save.setOnClickListener {
            if (name.text.isEmpty()) {
                Toast.makeText(this, "이름은 필수 값입니다", Toast.LENGTH_SHORT).show()
            }
            else if (phone.text.isEmpty()) {
                Toast.makeText(this, "전화 번호는 필수 값입니다", Toast.LENGTH_SHORT).show()
            }
            else{
                if (female.isChecked) {
                    sex = "여자"
                }
                else if (male.isChecked) {
                    sex = "남자"
                }
                // Insert data into database
                val contact = Contact(
                    name.text.toString(),
                    phone.text.toString().toInt(),
                    mail.text.toString(),
                    birth.text.toString(),
                    sex,
                    memo.text.toString()
                )
                database.contactDao().insert(contact)
                Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
            }

        }
        // Set click listener for cancel button
        fun loadAllData(){
            if (database.contactDao() == null){
                Log.d("contacttest", "null")
            }
            else{
                val list = database.contactDao().getAll()
                list.forEach {
                    Log.d("contacttest", ""+it.id+", "+it.name+", "+it.phone+", "+it.email+", "+it.birth+", "+it.sex+" ")
                }
            }
        }
        cancel.setOnClickListener {
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
//            loadAllData()
        }


    }

    // Show date picker dialog
    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this, {DatePicker, year:Int, month:Int, dayOfMonth:Int ->
                  val selectedDate = "$year.${month+1}.$dayOfMonth"
                birth.text = selectedDate
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }


}
