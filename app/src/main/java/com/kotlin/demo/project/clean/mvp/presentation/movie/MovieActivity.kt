package com.kotlin.demo.project.clean.mvp.presentation.movie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.demo.project.clean.mvp.R
import com.kotlin.demo.project.clean.mvp.domain.movie.Result
import com.kotlin.demo.project.clean.mvp.presentation.common.MVPView

class MovieActivity: AppCompatActivity(), MovieView {
    companion object {
        val TAG: String = MovieActivity::class.java.simpleName
    }

    private val presenter = MoviePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        presenter.onAttach(this)
        presenter.getMovie()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun onLoadingStart() {
        Log.d(TAG, "Loading started...")
    }

    override fun onLoadingFinish() {
        Log.d(TAG, "Loading finished...")
    }

    override fun onSuccess(entity: List<Result>) {
        Log.d(TAG, "${entity.size}")
    }

    override fun onError(error: String) {
        Log.d(TAG, "Error: ${error}")
    }
}