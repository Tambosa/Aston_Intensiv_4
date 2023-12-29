package com.example.aston_intensiv_4.di

import com.example.aston_intensiv_4.data.UserRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideUserRepo() = UserRepo()
}