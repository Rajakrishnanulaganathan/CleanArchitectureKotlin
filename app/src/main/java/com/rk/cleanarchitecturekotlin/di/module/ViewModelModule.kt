package com.rk.cleanarchitecturekotlin.di.module

import androidx.lifecycle.ViewModel
import com.rk.cleanarchitecturekotlin.viewmodel.MovieViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.reflect.KClass
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
@ExperimentalCoroutinesApi
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMyViewModel(viewModel: MovieViewModel): ViewModel
}