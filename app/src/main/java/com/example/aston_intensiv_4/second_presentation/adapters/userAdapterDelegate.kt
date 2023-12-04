package com.example.aston_intensiv_4.second_presentation.adapters

import coil.load
import coil.transform.CircleCropTransformation
import com.example.aston_intensiv_4.databinding.UserItemBinding
import com.example.aston_intensiv_4.domain.User
import com.example.aston_intensiv_4.domain.UserRecyclerItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun userAdapterDelegate(
    onItemClick: (item: User) -> Unit,
) =
    adapterDelegateViewBinding<User, UserRecyclerItem, UserItemBinding>({ layoutInflater, root ->
        UserItemBinding.inflate(layoutInflater, root, false)
    }) {
        binding.root.setOnClickListener { onItemClick(item) }
        bind {
            binding.userName.text = item.name
            binding.userSurname.text = item.surname
            binding.userPhoneNumber.text = item.phoneNumber
            binding.userImage.load(item.imageUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }