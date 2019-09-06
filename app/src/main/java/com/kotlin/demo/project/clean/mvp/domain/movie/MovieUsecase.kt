package com.kotlin.demo.project.clean.mvp.domain.movie

import com.kotlin.demo.project.clean.mvp.core.JobExecutor
import com.kotlin.demo.project.clean.mvp.core.UIThread
import com.kotlin.demo.project.clean.mvp.domain.common.Usecase
import io.reactivex.Single

class MovieUsecase(
    private val repository: MovieRepository
): Usecase<MovieEntity, Any>(
    JobExecutor(),
    UIThread()
) {
    override fun buildUsecaseObservable(params: Any): Single<MovieEntity> =
        repository.getMovie()
}