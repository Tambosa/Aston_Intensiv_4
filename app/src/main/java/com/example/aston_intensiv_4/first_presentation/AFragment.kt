package com.example.aston_intensiv_4.first_presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.aston_intensiv_4.R

class AFragment : Fragment(R.layout.fragment_a) {
    companion object {
        fun newInstance() = AFragment()

        const val A_FRAGMENT_TAG = "A_FRAGMENT_TAG"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_fragment_A_nav_to_B).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, BFragment.newInstance(), BFragment.B_FRAGMENT_TAG)
                .addToBackStack(BFragment.B_FRAGMENT_TAG)
                .setReorderingAllowed(true)
                .commit()
        }
    }
}