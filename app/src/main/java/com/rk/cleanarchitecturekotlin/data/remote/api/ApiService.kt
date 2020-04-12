package com.rk.cleanarchitecturekotlin.data.remote.api

import com.rk.cleanarchitecturekotlin.data.local.entity.MovieEntity
import com.rk.cleanarchitecturekotlin.data.local.entity.MovieResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovieResults(@Query("api_key") apikey: String?): Response<MovieResponse>


    @GET("/3/movie/{movieId}")
    suspend fun fetchMovieDetail(@Path("movieId") movieId: String?): Response<MovieEntity>
}