package com.example.aston_intensiv_4.di

import com.example.aston_intensiv_4.second_presentation.UserListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, DataModule::class])
interface AppComponent {
    fun inject(userListFragment: UserListFragment)
}