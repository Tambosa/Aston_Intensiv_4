package com.example.aston_intensiv_4

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import com.example.aston_intensiv_4.di.AppComponent

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? =
    BundleCompat.getParcelable(this, key, T::class.java)

fun Fragment.getAppComponent(): AppComponent = (requireContext().applicationContext as App).appComponent