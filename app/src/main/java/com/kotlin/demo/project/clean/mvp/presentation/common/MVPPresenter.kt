package com.kotlin.demo.project.clean.mvp.presentation.common

import com.kotlin.demo.project.clean.mvp.domain.common.Usecase

interface Presenter<T: MVPView> {
    fun onAttach(view: T)
    fun onDetach()
}

abstract class MVPPresenter<T: MVPView>(vararg usecases: Usecase<*, *>) : Presenter<T> {
    private lateinit var view: T

    private val listOfUsecase: List<Usecase<*, *>> = ArrayList()

    init { usecases.map { listOfUsecase.plus(it) } }

    override fun onAttach(view: T) {
        this.view = view
    }

    override fun onDetach() {
        listOfUsecase.map { it.dispose() }
    }

    protected fun getView(): T = view
}