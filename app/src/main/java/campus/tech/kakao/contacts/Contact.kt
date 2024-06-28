package com.example.contacts

data class Contact(
    val name: String,
    val phone: String,
    val email: String? = null,
    val birthday: String? = null,
    val gender: String? = null,
    val memo: String? = null
)
