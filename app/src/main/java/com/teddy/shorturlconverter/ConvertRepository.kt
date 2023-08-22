package com.teddy.shorturlconverter

import com.teddy.shorturlconverter.model.UrlResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ConvertRepository {
    val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getShortUrl(url: String): UrlResponse {
        val response = client.get("https://naveropenapi.apigw.ntruss.com/util/v1/shorturl") {
            url {
                parameters.append("url", url)
            }

            contentType(ContentType.Application.Json)

            headers {
                append("X-NCP-APIGW-API-KEY-ID", "063jcm79l3")
                append("X-NCP-APIGW-API-KEY", "Ywwv06t1nlWxWruWCPVjSkY6tN5hB4iAXOcQbavo")
            }
        }

        return response.body()
    }
}