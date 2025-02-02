package com.example.cred_assignment.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.cred_assignment.presentation.util.ErrorDisplay
import com.example.cred_assignment.presentation.util.Loader
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: CredViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    when {
        uiState.isLoading -> Loader(Modifier.fillMaxSize())

        uiState.error.isNotEmpty() -> ErrorDisplay(
            message = uiState.error,
            onRetry = { viewModel.fetchContent() }
        )

        uiState.content != null -> {
            StackFrameworkExample(content = uiState.content!!)
        }

        else -> {
            viewModel.fetchContent()
        }
    }
}