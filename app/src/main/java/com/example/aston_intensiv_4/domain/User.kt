package com.example.aston_intensiv_4.domain

import androidx.annotation.DrawableRes
import com.example.aston_intensiv_4.R

data class User(
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    @DrawableRes val imageUrl: Int = R.drawable.person_placeholder,
) : UserRecyclerItem
