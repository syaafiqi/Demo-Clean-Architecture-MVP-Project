package com.kotlin.demo.project.clean.mvp.presentation.movie

import com.kotlin.demo.project.clean.mvp.core.Network
import com.kotlin.demo.project.clean.mvp.core.response
import com.kotlin.demo.project.clean.mvp.data.movie.MovieDatasource
import com.kotlin.demo.project.clean.mvp.data.movie.MovieFactory
import com.kotlin.demo.project.clean.mvp.domain.common.DefaultObserver
import com.kotlin.demo.project.clean.mvp.domain.movie.MovieEntity
import com.kotlin.demo.project.clean.mvp.domain.movie.MovieRepository
import com.kotlin.demo.project.clean.mvp.domain.movie.MovieUsecase
import com.kotlin.demo.project.clean.mvp.presentation.common.MVPPresenter

class MoviePresenter : MVPPresenter<MovieView>() {
    private val datasource = Network.providesHttpAdapter()
        .create(MovieDatasource::class.java)
    private val factory = MovieFactory(datasource)
    private val repository = MovieRepository(factory)
    private val usecase = MovieUsecase(repository)

    fun getMovie() {
        getView().onLoadingStart()
        usecase.execute(MovieUsecaseObserver(), Any())
    }

    inner class MovieUsecaseObserver : DefaultObserver<MovieEntity>() {
        override fun onSuccess(t: MovieEntity) {
            getView().onLoadingFinish()
            getView().onSuccess(t.results)
        }

        override fun onError(e: Throwable) {
            getView().onLoadingFinish()
            getView().onError(e.response().statusMessage)
        }
    }
}