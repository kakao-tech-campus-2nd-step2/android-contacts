package campus.tech.kakao.contacts

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

class collection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //데이터 저장 및 로드
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_collection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //Room 데이터베이스 설정
    @Database(entities = [Contact::class],version = 1)
    abstract class AppDatabase :
    RoomDatabase() {
        abstract fun contactDao():ContactDao
    }
    //Contact 엔티티 및 DAO 인터페이스 생성
    @Entity
    data class Contact(
        @PrimaryKey(autoGenerate = true) val id:Int = 0,
        val name:String,
        val phone: String,
        val gender:String
    )
    @Dao
    interface ContactDao {
        @Insert
        suspend fun insert(contact: campus.tech.kakao.contacts.Contact)
        @Query("SELECT*FROM Contact")
        fun getAllContacts():List<Contact>
    }

    //RecyclerView를 이용한 이름 표시
    //이름 클릭 시 전체 정보 표시
}

class ContactDao {

}

class Contact {

}
