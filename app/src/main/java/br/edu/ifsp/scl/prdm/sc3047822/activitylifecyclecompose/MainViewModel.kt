package br.edu.ifsp.scl.prdm.sc3047822.activitylifecyclecompose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private companion object {
        const val USER_KEY = "user"
    }

    private val _uiState = MutableStateFlow(savedStateHandle[USER_KEY] ?: User())
    val uiState: StateFlow<User> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }

        savedStateHandle[USER_KEY] = _uiState.value.copy(name = name)
    }

    fun updateAge(age: Int?) {
        val finalAge = age ?: 0

        _uiState.update { it.copy(age = finalAge) }

        savedStateHandle[USER_KEY] = _uiState.value.copy(age = finalAge)
    }
}