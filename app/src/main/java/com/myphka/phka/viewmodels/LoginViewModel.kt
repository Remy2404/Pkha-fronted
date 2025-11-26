package com.myphka.phka.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.myphka.phka.repositories.LoginRepository

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

    // Use repository (simple instantiation; replace with DI in future)
    private val repository = LoginRepository()

    fun updateEmail(email: String) {
        Log.d("LoginViewModel", "updateEmail: $email")
        _uiState.value = _uiState.value.copy(email = email, error = null)
    }

    fun updatePassword(password: String) {
        Log.d("LoginViewModel", "updatePassword: <redacted>")
        _uiState.value = _uiState.value.copy(password = password, error = null)
    }

    fun login() {
        if (_uiState.value.email.isEmpty() || _uiState.value.password.isEmpty()) {
            _uiState.value = _uiState.value.copy(error = "Please fill in all fields")
            Log.d("LoginViewModel", "Login prevented: empty fields")
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        Log.d("LoginViewModel", "Starting login for: ${_uiState.value.email}")

        viewModelScope.launch {
            val email = _uiState.value.email
            val password = _uiState.value.password
            try {
                val result = repository.login(email, password)
                if (result.isSuccess) {
                    Log.d("LoginViewModel", "Login success for: $email")
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoginSuccessful = true,
                        error = null
                    )
                } else {
                    val ex = result.exceptionOrNull()
                    Log.d("LoginViewModel", "Login failed for: $email, reason=${ex?.message}")
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoginSuccessful = false,
                        error = ex?.message ?: "Login failed"
                    )
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Login error for: $email", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoginSuccessful = false,
                    error = e.message ?: "Login error"
                )
            }
        }
    }

    fun loginWithGoogle() {
        // Placeholder for Google login
    }

    fun loginWithApple() {
        // Placeholder for Apple login
    }
}
