package com.kotlin.demo.project.clean.mvp.domain.movie

import com.kotlin.demo.project.clean.mvp.core.Config
import com.kotlin.demo.project.clean.mvp.data.movie.MovieFactory
import io.reactivex.Single

class MovieRepository(private val factory: MovieFactory) {
    fun getMovie(): Single<MovieEntity> =
        factory.getMovie().map { response ->
            MovieEntity(
                results = response.results.map {
                    Result(
                        title = it.title,
                        posterPath = "${Config.IMAGE_PATH}/${it.posterPath}"
                    )
                }
            )
        }
}