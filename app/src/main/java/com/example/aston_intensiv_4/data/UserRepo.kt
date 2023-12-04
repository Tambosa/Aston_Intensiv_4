package com.example.aston_intensiv_4.data

import com.example.aston_intensiv_4.domain.User

class UserRepo {
    fun getUsers() = listOf(
        User(1, "Andrei", "Roman", "8900123001"),
        User(2, "John", "Smith", "8900123002"),
        User(3, "Maya", "Green", "8900123003"),
        User(4, "Alex", "Smithson", "8900123004"),
    )
}