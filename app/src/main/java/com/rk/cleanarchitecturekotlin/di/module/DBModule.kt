package com.rk.cleanarchitecturekotlin.di.module

import android.app.Application
import com.rk.cleanarchitecturekotlin.data.local.MovieDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun getMovieDatabase(app:Application): MovieDataBase {
        return MovieDataBase.invoke(
            app.applicationContext
        )
    }

    @Singleton
    @Provides
    fun providePostsDao(database: MovieDataBase) = database.getMovieDao()
}