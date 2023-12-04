package com.example.aston_intensiv_4.second_presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.Utils
import com.example.aston_intensiv_4.data.UserRepo
import com.example.aston_intensiv_4.domain.User
import com.example.aston_intensiv_4.domain.UserRecyclerItem
import com.example.aston_intensiv_4.second_presentation.adapters.UserListCompositeAdapter

class UserListFragment : Fragment(R.layout.fragment_user_list) {
    private lateinit var viewModel: UserListViewModel
    private val userListAdapter = UserListCompositeAdapter(
        onUserClick = { item -> onUserClick(item) }
    )

    companion object{
        const val USER_LIST_FRAGMENT_TAG = "USER_LIST_FRAGMENT_TAG"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = UserListViewModel(UserRepo())
        initRecycler()
        initViewModel()
    }

    private fun initRecycler() {
        view?.findViewById<RecyclerView>(R.id.user_list_recycler_view)?.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
        }
    }

    private fun initViewModel() {
        viewModel.state.observe(requireActivity()) { state ->
            state?.let {
                renderRecyclerData(state)
            }
        }
        viewModel.getState()
    }

    private fun renderRecyclerData(newList: List<UserRecyclerItem>) {
        val oldList = userListAdapter.items ?: listOf()
        val diff = Utils.getStandardDiff(oldList, newList)
        userListAdapter.items = newList
        diff.dispatchUpdatesTo(userListAdapter)
    }

    private fun onUserClick(item: User) {

    }
}