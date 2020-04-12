package com.rk.cleanarchitecturekotlin.di.module

import androidx.lifecycle.ViewModelProvider
import com.rk.cleanarchitecturekotlin.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}