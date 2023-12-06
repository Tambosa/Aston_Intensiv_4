package com.example.aston_intensiv_4

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_intensiv_4.first_presentation.AFragment
import com.example.aston_intensiv_4.second_presentation.UserListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_activity_homework_1).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, AFragment.newInstance(), AFragment.A_FRAGMENT_TAG)
                .addToBackStack(AFragment.A_FRAGMENT_TAG)
                .setReorderingAllowed(true)
                .commit()
        }

        findViewById<Button>(R.id.btn_activity_homework_2).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.main_container,
                    UserListFragment(),
                    UserListFragment.USER_LIST_FRAGMENT_TAG
                )
                .addToBackStack(UserListFragment.USER_LIST_FRAGMENT_TAG)
                .setReorderingAllowed(true)
                .commit()
        }
    }
}