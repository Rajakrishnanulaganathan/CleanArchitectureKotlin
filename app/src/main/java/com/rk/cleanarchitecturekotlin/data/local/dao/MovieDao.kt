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
    fun insertMovies(movieEntity: List<MovieEntity?>?): LongArray?

    @Query("select * from movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("select * from movies where original_title=:id")
    fun getMovie(id: String): Flow<MovieEntity>
}