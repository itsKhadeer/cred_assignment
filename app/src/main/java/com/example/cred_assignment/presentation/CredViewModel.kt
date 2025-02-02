package com.example.cred_assignment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cred_assignment.data.datasource.NetworkResult
import com.example.cred_assignment.domain.repository.CredRepository
import com.example.stack_frame_work.StackState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CredViewModel(
    private val repository: CredRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<CredUIState>(CredUIState())
    val uiState: StateFlow<CredUIState> = _uiState.asStateFlow()

    private val _amount = MutableStateFlow<Int>(0)
    val amount: StateFlow<Int> = _amount.asStateFlow()

    private val _plan = MutableStateFlow<Int>(0)
    val plan: StateFlow<Int> = _plan.asStateFlow()

    private val _bank = MutableStateFlow<Int>(0)
    val bank: StateFlow<Int> = _bank.asStateFlow()

    private val _stackState = MutableStateFlow(StackState())
    val stackState: StateFlow<StackState> = _stackState.asStateFlow()

    val changeAmount = { change: Int -> _amount.value = change }
    val changePlan = { change: Int -> _plan.value = change }
    val changeBank = { change: Int -> _bank.value = change }

    init {
        fetchContent()
    }

    fun fetchContent() {
        _uiState.value = _uiState.value.copy(isLoading = true, content = null)
        viewModelScope.launch {
            val result = repository.getCredContent()
            when (result) {
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, content = result.data)
                }

                is NetworkResult.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = result.message)
                }
            }
        }
    }
}
