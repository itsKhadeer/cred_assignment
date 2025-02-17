package com.example.cred_assignment.presentation.util

import com.example.cred_assignment.domain.models.ContentModel


data class CredUIState (
    val isLoading: Boolean = false,
    val content: ContentModel? = null,
    val error: String = ""
)