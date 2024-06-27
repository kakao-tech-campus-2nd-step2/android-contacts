package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //view 설정
        noContactsText = findViewById<TextView>(R.id.noContactsText)
        contactsRecyclerView = findViewById<RecyclerView>(R.id.contactsRecyclerView)
        addContactButton = findViewById<FloatingActionButton>(R.id.addContactButton)

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

    //요청 코드
    companion object {
        const val REQUEST_CODE_ADD = 1
    }

    //UI 업데이트
    private fun UpdateUI(){
        //contactslist 비어있는 경우
        if (contactsList.isEmpty()) {
            noContactsText.visibility = TextView.VISIBLE
            contactsRecyclerView.visibility = RecyclerView.GONE
        } else { //연락처 하나 이상 있는 경우
            noContactsText.visibility = TextView.GONE
            contactsRecyclerView.visibility = RecyclerView.VISIBLE
        }
    }
}
