package com.rk.cleanarchitecturekotlin.data.remote

import com.rk.cleanarchitecturekotlin.data.local.dao.MovieDao
import com.rk.cleanarchitecturekotlin.data.local.entity.MovieEntity
import com.rk.cleanarchitecturekotlin.data.local.entity.MovieResponse
import com.rk.cleanarchitecturekotlin.data.remote.api.ApiService
import com.rk.cleanarchitecturekotlin.di.repository.NetworkBoundRepository
import com.rk.cleanarchitecturekotlin.utils.Constatnts
import com.rk.cleanarchitecturekotlin.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviedao:MovieDao,
    private val apiService: ApiService
) {


    fun getAllpopularmovies(): Flow<State<List<MovieEntity>>> {
        return object : NetworkBoundRepository<List<MovieEntity> , MovieResponse>(),
            Flow<State<MovieResponse>> {
            var movieEntities: MutableList<MovieEntity> = mutableListOf<MovieEntity>()

            override suspend fun saveRemoteData(response: MovieResponse) {
                response.results?.let {
                    for(movieEntity in it){
                        movieEntity.page=response.page?.toLong()
                        movieEntity.totalPages=response.total_pages?.toLong()
                        movieEntities.add(movieEntity)
                    }
                }



               moviedao.insertmovies(movieEntities)
            }

            override fun fetchFromLocal(): Flow<List<MovieEntity>> {
               return moviedao.getallmovies()
            }

            override suspend fun fetchFromRemote(): Response<MovieResponse> {
                return apiService.getPopularMovieResults(Constatnts.API_KEY)
            }

            override suspend fun collect(collector: FlowCollector<State<MovieResponse>>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun getAllpopularmoviesDetails(id:String): Flow<MovieEntity> {
       return moviedao.getmovie(id)
    }
}