package com.example.stack_frame_work

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


// State holder for the stack framework
class StackState {
    private var _expandedIndex by mutableStateOf<Int?>(null)
    val expandedIndex: Int? get() = _expandedIndex

    fun toggleExpanded(index: Int) {
        if(_expandedIndex == index && index > 0) {
            _expandedIndex = index -  1
        } else if(_expandedIndex == 0 && index == 0) {
            _expandedIndex = null
        } else {
            _expandedIndex = index
        }
    }
}