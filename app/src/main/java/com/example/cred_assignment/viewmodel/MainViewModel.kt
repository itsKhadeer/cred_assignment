package com.example.cred_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cred_assignment.models.Content
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

sealed class UiState {
    data object Loading : UiState()
    data class Success(val content: Content) : UiState()
    data class Error(val message: String) : UiState()
}

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(json = json)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }

        install(HttpCache)
    }

    init {
        fetchContent()
    }

    fun fetchContent() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response =
                    httpClient.get("https://api.mocklets.com/p6764/test_mint").body<Content>()
                _uiState.value = UiState.Success(response)
                Log.d("MainViewModel", "Content fetched successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error occurred")
                Log.e("MainViewModel", "Error fetching content: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        httpClient.close()
    }
}
