package com.kotlin.demo.project.clean.mvp.data.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("status_code")
    val statusCode: Int = -1,
    @SerialName("status_message")
    val statusMessage: String = "Unknown Response.",
    @SerialName("success")
    val isSuccess: Boolean = false
)