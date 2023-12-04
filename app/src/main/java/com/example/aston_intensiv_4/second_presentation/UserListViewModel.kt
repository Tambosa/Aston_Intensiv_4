package com.example.aston_intensiv_4.second_presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.aston_intensiv_4.data.UserRepo
import com.example.aston_intensiv_4.domain.User
import com.example.aston_intensiv_4.domain.UserRecyclerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val repo: UserRepo) : ViewModel() {
    private val _state = MutableLiveData<MutableList<UserRecyclerItem>>()
    val state: LiveData<List<UserRecyclerItem>> = _state.map {
        it.toList()
    }

    fun getState() {
        if (state.value == null) {
            _state.value = mutableListOf<UserRecyclerItem>().apply {
                addAll(repo.getUsers())
                sortWith(compareBy(String.CASE_INSENSITIVE_ORDER) { (it as User).name })
            }
        }
    }

    fun updateUser(updatedUser: User) {
        _state.value = _state.value?.apply {
            this.remove(find { (it as User).id == updatedUser.id })
            this.add(updatedUser)
        }
    }
}