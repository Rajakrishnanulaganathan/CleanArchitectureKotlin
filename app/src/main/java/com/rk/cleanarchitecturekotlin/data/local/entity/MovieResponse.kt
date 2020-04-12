package com.rk.cleanarchitecturekotlin.data.local.entity


data class MovieResponse(
    var page: Int? = null,
    var total_results: Int? = null,
    var total_pages: Int? = null,
    var results: List<MovieEntity>? = null){
}
