package com.example.aston_intensiv_4.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aston_intensiv_4.di.utils.ViewModelFactory
import com.example.aston_intensiv_4.di.utils.ViewModelKey
import com.example.aston_intensiv_4.second_presentation.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    internal abstract fun bindUserListViewModel(viewModel: UserListViewModel): ViewModel
}