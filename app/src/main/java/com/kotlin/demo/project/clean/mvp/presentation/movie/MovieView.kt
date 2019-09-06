package com.kotlin.demo.project.clean.mvp.presentation.movie

import com.kotlin.demo.project.clean.mvp.domain.movie.Result
import com.kotlin.demo.project.clean.mvp.presentation.common.MVPView

interface MovieView : MVPView {
    fun onSuccess(entity: List<Result>)
    fun onError(error: String)
}