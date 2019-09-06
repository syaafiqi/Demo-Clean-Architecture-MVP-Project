package com.kotlin.demo.project.clean.mvp.data.movie

import com.kotlin.demo.project.clean.mvp.BuildConfig
import com.kotlin.demo.project.clean.mvp.core.Config
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDatasource {
    @GET("${Config.API_VERSION}/discover/movie")
    fun getMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.KEY
    ): Single<MovieResponse>
}