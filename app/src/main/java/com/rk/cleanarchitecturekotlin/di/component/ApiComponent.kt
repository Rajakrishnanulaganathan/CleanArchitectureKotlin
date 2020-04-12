package com.rk.cleanarchitecturekotlin.di.component

import android.app.Application
import com.rk.cleanarchitecturekotlin.MovieApplication
import com.rk.cleanarchitecturekotlin.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton
@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [AndroidInjectionModule::class,FragmentModule::class,AndroidSupportInjectionModule::class, ApiModule::class, DBModule::class, ViewModelModule::class,ActivityModule::class, ViewModelFactoryModule::class])
interface ApiComponent: AndroidInjector<MovieApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): ApiComponent
    }

    override fun inject(app: MovieApplication)
}