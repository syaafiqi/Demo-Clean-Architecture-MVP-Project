package com.kotlin.demo.project.clean.mvp.domain.common

import com.kotlin.demo.project.clean.mvp.core.PostExecutionThread
import com.kotlin.demo.project.clean.mvp.core.ThreadExecutor
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class Usecase<T, in Params>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {
    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildUsecaseObservable(params: Params): Single<T>

    fun execute(observer: DefaultObserver<T>, params: Params) {
        val single: Single<T> = buildUsecaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
        addDisposable(single.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        disposables.clear()
    }
}