package com.teddy.shorturlconverter.model

import kotlinx.serialization.Serializable

@Serializable
data class UrlResponse(
    val message: String,
    val result: Result,
    val code: Int
)

@Serializable
data class Result(
    val hash: String,
    val url: String,
    val orgUrl: String
)
