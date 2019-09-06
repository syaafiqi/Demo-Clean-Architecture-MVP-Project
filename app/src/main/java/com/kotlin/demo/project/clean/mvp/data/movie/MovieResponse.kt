package com.kotlin.demo.project.clean.mvp.data.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val results: List<Result>
)

@Serializable
data class Result(
    val id: Long,

    @SerialName("poster_path")
    val posterPath: String,
    val title: String,
    val overview: String
)