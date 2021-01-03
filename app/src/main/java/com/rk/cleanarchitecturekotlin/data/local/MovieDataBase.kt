package com.rk.cleanarchitecturekotlin.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rk.cleanarchitecturekotlin.data.local.dao.MovieDao
import com.rk.cleanarchitecturekotlin.data.local.entity.MovieEntity
import com.rk.cleanarchitecturekotlin.utils.Constatnts

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDataBase:RoomDatabase() {
    abstract fun  getMovieDao(): MovieDao
    companion object{
        @Volatile
        private var instance: MovieDataBase?=null

        private var LOCK=Any()

        operator fun invoke(context: Context)= instance
            ?: synchronized(LOCK){
            instance
                ?: buildDatabase(
                    context
                ).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context): MovieDataBase {
            return Room.databaseBuilder(context,
                MovieDataBase::class.java,Constatnts.DB_NAME).allowMainThreadQueries().build()

        }
    }
}