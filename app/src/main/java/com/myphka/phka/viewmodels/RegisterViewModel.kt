package com.myphka.phka.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val termsAccepted: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRegisterSuccessful: Boolean = false
)

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
    }

    fun updateTermsAccepted(accepted: Boolean) {
        _uiState.value = _uiState.value.copy(termsAccepted = accepted)
    }

    fun register() {
        if (_uiState.value.name.isEmpty() || _uiState.value.email.isEmpty() ||
            _uiState.value.password.isEmpty() || _uiState.value.confirmPassword.isEmpty()
        ) {
            _uiState.value = _uiState.value.copy(error = "Please fill in all fields")
            return
        }

        if (_uiState.value.password != _uiState.value.confirmPassword) {
            _uiState.value = _uiState.value.copy(error = "Passwords do not match")
            return
        }

        if (!_uiState.value.termsAccepted) {
            _uiState.value = _uiState.value.copy(error = "Please accept terms and conditions")
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true)
        // Simulate registration
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isRegisterSuccessful = true
        )
    }
}
