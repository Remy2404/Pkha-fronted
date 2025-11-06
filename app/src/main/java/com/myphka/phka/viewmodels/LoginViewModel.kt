package com.myphka.phka.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoginSuccessful: Boolean = false
)

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
        if (_uiState.value.email.isEmpty() || _uiState.value.password.isEmpty()) {
            _uiState.value = _uiState.value.copy(error = "Please fill in all fields")
            return
        }
        _uiState.value = _uiState.value.copy(isLoading = true)
        // Simulate login
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isLoginSuccessful = true
        )
    }

    fun loginWithGoogle() {
        // Placeholder for Google login
    }

    fun loginWithApple() {
        // Placeholder for Apple login
    }
}
