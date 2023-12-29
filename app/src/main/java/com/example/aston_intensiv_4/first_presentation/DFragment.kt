package com.example.aston_intensiv_4.first_presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.aston_intensiv_4.R

class DFragment : Fragment(R.layout.fragment_d) {
    companion object {
        fun newInstance() = DFragment()

        const val D_FRAGMENT_TAG = "D_FRAGMENT_TAG"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_fragment_D_nav_to_B).setOnClickListener {
            parentFragmentManager.popBackStack(BFragment.B_FRAGMENT_TAG, 0)
        }
    }
}