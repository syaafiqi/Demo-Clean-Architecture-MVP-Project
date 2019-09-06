package com.kotlin.demo.project.clean.mvp.core

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kotlin.demo.project.clean.mvp.BuildConfig
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


@UnstableDefault
object Network {
    fun providesHttpAdapter(): Retrofit =
        Retrofit.Builder().apply {
            client(providesHttpClient())
            baseUrl(BuildConfig.BASE_URL)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(Json.nonstrict.asConverterFactory("application/json".toMediaType()))
        }.build()

    private fun providesHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            addInterceptor(providesHttpLoggingInterceptor())
        }.build()

    private fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
}