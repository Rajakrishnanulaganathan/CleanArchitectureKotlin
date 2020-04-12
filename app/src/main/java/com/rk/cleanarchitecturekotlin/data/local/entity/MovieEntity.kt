package com.rk.cleanarchitecturekotlin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies")
data class MovieEntity(
    var page: Long? = null,
    var totalPages: Long? = null,
    var popularity: Double? = null,
    var vote_count: Int? = null,
    var video: Boolean? = null,
    var poster_path: String? = null,
    @PrimaryKey
    var id: Int? =0,
    var adult: Boolean? = null,
    var backdrop_path: String? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var title: String? = null,
    var vote_average: Double? = null,
    var overview: String? = null,
    var release_date: String? = null){}

