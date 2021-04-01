package com.rk.cleanarchitecturekotlin.di.module

import com.rk.cleanarchitecturekotlin.ui.MovieFragment
import com.rk.cleanarchitecturekotlin.ui.MovieDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun moviefragment():MovieFragment

    @ContributesAndroidInjector
    abstract fun  movieDetailFragment(): MovieDetailsFragment
}