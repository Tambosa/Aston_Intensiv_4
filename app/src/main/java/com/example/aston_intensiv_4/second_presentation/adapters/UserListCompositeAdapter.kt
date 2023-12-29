package com.example.aston_intensiv_4.second_presentation.adapters

import com.example.aston_intensiv_4.domain.User
import com.example.aston_intensiv_4.domain.UserRecyclerItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class UserListCompositeAdapter(
    onUserClick: (item: User) -> Unit,
) : ListDelegationAdapter<List<UserRecyclerItem>>(
    userAdapterDelegate(onItemClick = onUserClick)
)