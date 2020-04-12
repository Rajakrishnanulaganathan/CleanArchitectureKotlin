package com.rk.cleanarchitecturekotlin.di.module

import com.rk.cleanarchitecturekotlin.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class ActivityModule {
    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector
    abstract fun bindMainActivity():MainActivity
}