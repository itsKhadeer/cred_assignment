package com.example.stack_frame_work

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class StackState {
    private var _expandedIndex by mutableStateOf<Int?>(null)
    val expandedIndex: Int? get() = _expandedIndex

    fun toggleExpanded(index: Int?) {

        if (index != null) {
            _expandedIndex = index
        } else {
            _expandedIndex = null
        }
    }
}