package com.example.aston_intensiv_4.second_presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.Utils
import com.example.aston_intensiv_4.domain.User
import com.example.aston_intensiv_4.domain.UserRecyclerItem
import com.example.aston_intensiv_4.second_presentation.UserDetailsFragment.Companion.USER_DETAILS_FRAGMENT_TAG
import com.example.aston_intensiv_4.second_presentation.adapters.UserListCompositeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {
    private lateinit var viewModel: UserListViewModel
    private val userListAdapter = UserListCompositeAdapter(
        onUserClick = { item -> onUserClick(item) }
    )

    companion object {
        const val USER_LIST_FRAGMENT_TAG = "USER_LIST_FRAGMENT_TAG"
        const val FRAGMENT_RESULT_KEY_UPDATE_USER = "FRAGMENT_RESULT_KEY_UPDATE_USER"
        const val USER_RESULT_ID = "USER_RESULT_ID"
        const val USER_RESULT_NAME = "USER_RESULT_NAME"
        const val USER_RESULT_SURNAME = "USER_RESULT_SURNAME"
        const val USER_RESULT_PHONE_NUMBER = "USER_RESULT_PHONE_NUMBER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        initListeners()
    }

    private fun initListeners() {
        parentFragmentManager.setFragmentResultListener(
            FRAGMENT_RESULT_KEY_UPDATE_USER, this
        ) { _, bundle ->
            val id = bundle.getInt(USER_RESULT_ID)
            val name = bundle.getString(USER_RESULT_NAME)
            val surname = bundle.getString(USER_RESULT_SURNAME)
            val phoneNumber = bundle.getString(USER_RESULT_PHONE_NUMBER)

            if (name != null && surname != null && phoneNumber != null) {
                viewModel.updateUser(User(id, name, surname, phoneNumber))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.state.observe(requireActivity()) { state ->
            state?.let {
                renderRecyclerData(state)
            }
        }
        viewModel.getState()
    }

    private fun initRecycler() {
        view?.findViewById<RecyclerView>(R.id.user_list_recycler_view)?.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
        }
    }

    private fun renderRecyclerData(newList: List<UserRecyclerItem>) {
        val oldList = userListAdapter.items ?: listOf()
        val diff = Utils.getStandardDiff(oldList, newList)
        userListAdapter.items = newList
        diff.dispatchUpdatesTo(userListAdapter)
    }

    private fun onUserClick(item: User) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.main_container,
                UserDetailsFragment.newInstance(item),
                USER_DETAILS_FRAGMENT_TAG,
            )
            .addToBackStack(USER_DETAILS_FRAGMENT_TAG)
            .setReorderingAllowed(true)
            .commit()
    }
}