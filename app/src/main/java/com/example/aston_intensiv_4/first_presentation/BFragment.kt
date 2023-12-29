package com.example.aston_intensiv_4.first_presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.aston_intensiv_4.R

class BFragment : Fragment(R.layout.fragment_b) {
    companion object {
        fun newInstance() = BFragment()

        const val B_FRAGMENT_TAG = "B_FRAGMENT_TAG"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_fragment_B_nav_to_C).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main_container,
                    CFragment.newInstance("Hello Fragment C"),
                    CFragment.C_FRAGMENT_TAG
                )
                .addToBackStack(CFragment.C_FRAGMENT_TAG)
                .setReorderingAllowed(true)
                .commit()
        }
        view.findViewById<Button>(R.id.btn_fragment_B_nav_back).setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}