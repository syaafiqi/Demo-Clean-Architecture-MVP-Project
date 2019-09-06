package com.kotlin.demo.project.clean.mvp.data.movie

import io.reactivex.Single

class MovieFactory(private val datasource: MovieDatasource) {
    fun getMovie(): Single<MovieResponse> = datasource.getMovie()
}