package com.example.aston_intensiv_4.first_presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.aston_intensiv_4.R

private const val MESSAGE = "MESSAGE"

class CFragment : Fragment(R.layout.fragment_c) {
    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString(MESSAGE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.text_fragment_c).text = message
        view.findViewById<Button>(R.id.btn_fragment_C_nav_to_D).setOnClickListener {
            parentFragmentManager.beginTransaction().replace(
                    R.id.main_container, DFragment.newInstance(), DFragment.D_FRAGMENT_TAG
                ).addToBackStack(DFragment.D_FRAGMENT_TAG).setReorderingAllowed(true).commit()
        }
        view.findViewById<Button>(R.id.btn_fragment_C_nav_to_A).setOnClickListener {
            parentFragmentManager.popBackStack(AFragment.A_FRAGMENT_TAG, 0)
        }
    }

    companion object {
        fun newInstance(message: String) = CFragment().apply {
            arguments = bundleOf(MESSAGE to message)
        }

        const val C_FRAGMENT_TAG = "C_FRAGMENT_TAG"
    }
}