package com.teddy.shorturlconverter.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Response

@Serializable
data class UrlResponse(
    val message: String,
    val result: Result,
    val code: Int
) : Response()

@Serializable
data class Error(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String,
    val code: Int
) : Response()

@Serializable
data class Result(
    val hash: String,
    val url: String,
    val orgUrl: String
)
