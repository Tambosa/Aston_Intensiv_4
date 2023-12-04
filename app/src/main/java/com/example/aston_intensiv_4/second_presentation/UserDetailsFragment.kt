package com.example.aston_intensiv_4.second_presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.databinding.FragmentUserDetailsBinding
import com.example.aston_intensiv_4.domain.User


class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {
    private var user: User? = null
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = if (Build.VERSION.SDK_INT >= 33) {
                it.getParcelable(USER, User::class.java)
            } else {
                it.getParcelable(USER)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.editName.setText(user?.name)
        binding.editSurname.setText(user?.surname)
        binding.editPhoneNumber.setText(user?.phoneNumber)
        binding.btnSave.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                UserListFragment.FRAGMENT_RESULT_KEY_UPDATE_USER,
                bundleOf(
                    UserListFragment.USER_RESULT_ID to user!!.id,
                    UserListFragment.USER_RESULT_NAME to binding.editName.text.toString(),
                    UserListFragment.USER_RESULT_SURNAME to binding.editSurname.text.toString(),
                    UserListFragment.USER_RESULT_PHONE_NUMBER to binding.editPhoneNumber.text.toString(),
                )
            )
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(user: User) = UserDetailsFragment().apply {
            arguments = bundleOf(USER to user)
        }

        private const val USER = "USER"
        const val USER_DETAILS_FRAGMENT_TAG = "USER_DETAILS_FRAGMENT_TAG"
    }
}