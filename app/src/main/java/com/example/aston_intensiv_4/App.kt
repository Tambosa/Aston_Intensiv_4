package com.example.aston_intensiv_4

import android.app.Application
import com.example.aston_intensiv_4.di.AppComponent
import com.example.aston_intensiv_4.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}