package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //전체
    lateinit var noContactsText: TextView
    lateinit var contactsRecyclerView: RecyclerView
    lateinit var addContactButton: FloatingActionButton
    val contactsList = mutableListOf<Contact>() //list 조회

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //view 설정
        noContactsText = findViewById(R.id.noContactsText)
        contactsRecyclerView = findViewById(R.id.contactsRecyclerView)
        addContactButton = findViewById(R.id.addContactButton)

        //Recyclerview - 수직 배열 & 세부 조회 (5,6번 기능 한 번에 commit함)
        contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        contactsRecyclerView.adapter =
            ContactsAdapter(contactsList) {
                    contact ->
                //세부 조회
                val intent = Intent(this, ContactDetailActivity::class.java)

                //intent 담아서 전달
                intent.putExtra("contact", contact)
                startActivity(intent)
        }

        //연락처 추가 floating click listener
        addContactButton.setOnClickListener{
            //새 intent 객체 create
            val intent = Intent(
                this,
                ContactAddActivity::class.java
            )
            //추가 후 요청 결과 받기
            startActivityForResult(intent, REQUEST_CODE_ADD)
        }
        updateUI()
    }

    // 연락처 추가 시 비교
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //요청 코드 비교
        if(requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            val intentData = data

            //parcelable 객체 추출
            val parcelableContact = intentData?.getParcelableExtra<Contact>("contact")

            //null 여부 확인
            if (parcelableContact != null) {
                val contact = parcelableContact

                //연락처 리스트에 추가
                contactsList.add(contact)

                updateUI()
            }
        }
    }

    //요청 코드
    companion object {
        const val REQUEST_CODE_ADD = 1
    }

    //UI 업데이트
    private fun updateUI(){
        //contactslist 비어있는 경우
        if (contactsList.isEmpty()) {
            noContactsText.visibility = TextView.VISIBLE
            contactsRecyclerView.visibility = RecyclerView.GONE
        } else { //연락처 하나 이상 있는 경우
            noContactsText.visibility = TextView.GONE
            contactsRecyclerView.visibility = RecyclerView.VISIBLE
            contactsRecyclerView.adapter?.notifyDataSetChanged() //adapter에 추가된 데이터 변경사항 알리기
        }
    }
}
