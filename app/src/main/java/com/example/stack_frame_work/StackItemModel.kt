package com.example.stack_frame_work

import androidx.compose.runtime.Composable

data class StackItem(
    val id: Int,
    val content: @Composable () -> Unit,
    val collapsedContent: @Composable () -> Unit,
    val callToActionContent: @Composable () -> Unit = {}
)
