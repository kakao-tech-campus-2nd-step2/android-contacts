package com.example.contacts

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.contacts.R

class ContactDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val phoneEditText: EditText = findViewById(R.id.phoneEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val birthdayEditText: EditText = findViewById(R.id.birthdayEditText)
        val genderEditText: EditText = findViewById(R.id.genderEditText)
        val memoEditText: EditText = findViewById(R.id.memoEditText)

        val name = intent.getStringExtra("name") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""
        val email = intent.getStringExtra("email")
        val birthday = intent.getStringExtra("birthday")
        val gender = intent.getStringExtra("gender")
        val memo = intent.getStringExtra("memo")

        nameEditText.setText(name)
        phoneEditText.setText(phone)
        email?.let { emailEditText.setText(it) }
        birthday?.let { birthdayEditText.setText(it) }
        gender?.let { genderEditText.setText(it) }
        memo?.let { memoEditText.setText(it) }

        emailEditText.visibility = if (email.isNullOrEmpty()) View.GONE else View.VISIBLE
        birthdayEditText.visibility = if (birthday.isNullOrEmpty()) View.GONE else View.VISIBLE
        genderEditText.visibility = if (gender.isNullOrEmpty()) View.GONE else View.VISIBLE
        memoEditText.visibility = if (memo.isNullOrEmpty()) View.GONE else View.VISIBLE

        nameEditText.isEnabled = false
        phoneEditText.isEnabled = false
        emailEditText.isEnabled = false
        birthdayEditText.isEnabled = false
        genderEditText.isEnabled = false
        memoEditText.isEnabled = false
    }
}
