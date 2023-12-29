package com.example.aston_intensiv_4.second_presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.databinding.FragmentUserDetailsBinding
import com.example.aston_intensiv_4.domain.User
import com.example.aston_intensiv_4.parcelable
import com.example.aston_intensiv_4.second_presentation.UserListFragment.Companion.USER_BUNDLE_KEY


class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {
    private var user: User? = null
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val photoPickerLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                user?.let {
                    user = it.copy(imageUri = uri)
                    saveUserToArgs(user!!)
                }
                loadImage(uri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val user = it.parcelable<User>(USER_BUNDLE_KEY)
            if (user != null) {
                this.user = user
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
        initOnClicks()
    }

    private fun initViews() {
        binding.editName.setText(user?.name)
        binding.editSurname.setText(user?.surname)
        binding.editPhoneNumber.setText(user?.phoneNumber)
        loadImage(user?.imageUri)
    }

    private fun loadImage(uri: Uri?) {
        binding.imageProfile.load(uri ?: R.drawable.person_placeholder) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    private fun initOnClicks() {
        initBtnSave()
        initImageClick()
    }

    private fun initImageClick() {
        binding.imageProfile.setOnClickListener {
            photoPickerLauncher.launch(
                PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    .build()
            )
        }
    }

    private fun initBtnSave() {
        binding.btnSave.setOnClickListener {
            val user = User(
                user!!.id,
                binding.editName.text.toString(),
                binding.editSurname.text.toString(),
                binding.editPhoneNumber.text.toString(),
                user!!.imageUri
            )

            parentFragmentManager.setFragmentResult(
                UserListFragment.FRAGMENT_RESULT_KEY_UPDATE_USER,
                bundleOf(USER_BUNDLE_KEY to user)
            )
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveUserToArgs(user: User) {
        arguments = bundleOf(USER_BUNDLE_KEY to user)
    }

    companion object {
        fun newInstance(user: User) = UserDetailsFragment().apply {
            saveUserToArgs(user)
        }

        const val USER_DETAILS_FRAGMENT_TAG = "USER_DETAILS_FRAGMENT_TAG"
    }
}