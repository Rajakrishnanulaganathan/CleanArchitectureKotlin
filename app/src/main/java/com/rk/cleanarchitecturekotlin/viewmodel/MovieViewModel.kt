package com.rk.cleanarchitecturekotlin.viewmodel

import androidx.lifecycle.*
import com.rk.cleanarchitecturekotlin.data.local.entity.MovieEntity
import com.rk.cleanarchitecturekotlin.data.remote.MovieRepository
import com.rk.cleanarchitecturekotlin.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _postsLiveData = MutableLiveData<State<List<MovieEntity>>>()
    private val movieDetailsLiveData = MutableLiveData<MovieEntity>()

    val postsLiveData: LiveData<State<List<MovieEntity>>>
        get() = _postsLiveData

    val mMovieDetailsLiveData: LiveData<MovieEntity>
        get() = movieDetailsLiveData

    fun getPosts() {
        viewModelScope.launch {
            movieRepository.getAllPopularMovies().collect {
                _postsLiveData.value = it
            }
        }
    }

    fun getMoviesDetails(id: String) = movieRepository.getAllPopularMoviesDetails(id).asLiveData()

}
