package com.rk.cleanarchitecturekotlin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rk.cleanarchitecturekotlin.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertmovies(movieEntity: List<MovieEntity?>?): LongArray?

    @Query("select * from movies")
    fun getallmovies(): Flow<List<MovieEntity>>

    @Query("select * from movies where original_title=:id")
     fun getmovie(id: String): Flow<MovieEntity>
}