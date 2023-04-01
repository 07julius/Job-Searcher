package de.julius.jobsearcherandroid.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

abstract class API(
    open val ktorClient: HttpClient,
    open val baseUrl: String,
    open val builder: HttpRequestBuilder.() -> Unit = {}
) {
    protected suspend inline fun <reified T> subRequest(
        subUrl: String,
        block: HttpRequestBuilder.() -> Unit = {},
    ): T? = request(baseUrl + subUrl, block)

    protected suspend inline fun <reified T> request(
        url: String,
        block: HttpRequestBuilder.() -> Unit = {},
    ): T? {
        println("requesting url $url")
        val response: HttpResponse = ktorClient.get(url) {
            builder()

            block()
        }

        return if (response.status.isSuccess()) {
            response.receive()
        } else {
            null
        }
    }


    companion object {
        val json = Json {
            ignoreUnknownKeys = true
        }

        val defaultKtorClient = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    json
                )
            }
        }

        const val defaultBaseUrl = ""
    }

}
