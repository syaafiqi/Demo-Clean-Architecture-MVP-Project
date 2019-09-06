package com.kotlin.demo.project.clean.mvp.core

import com.kotlin.demo.project.clean.mvp.data.common.Response
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import retrofit2.HttpException

@UnstableDefault
internal fun Throwable.response(): Response {
    var response = Response()
    val httpException = this as? HttpException
    httpException?.response()?.errorBody()?.let { result ->
        val json = result.source().readUtf8()
        response = Json.parse(Response.serializer(), json)
    }

    return response
}