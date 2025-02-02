package com.example.cred_assignment.data.repository

import com.example.cred_assignment.data.datasource.api.CredApiDataSource
import com.example.cred_assignment.data.datasource.api.entity.Content
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CredApiDataSourceImpl : CredApiDataSource {
    companion object {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }
            install(HttpCache)
        }
    }

    override suspend fun getCredContent(): Content {
        return httpClient.get("https://api.mocklets.com/p6764/test_mint").body<Content>()
    }
}