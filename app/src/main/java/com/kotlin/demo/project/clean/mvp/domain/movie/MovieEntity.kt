package com.kotlin.demo.project.clean.mvp.domain.movie

data class MovieEntity(val results: List<Result>)

data class Result(
    val title: String,
    val posterPath: String
)