package com.myphka.phka.viewmodels.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    val name: String,
    val email: String,
    val avatarUrl: String? = null
)

data class ProfileUiState(
    val userProfile: UserProfile? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        // Mock data loading
        viewModelScope.launch {
            // Simulate network delay
            kotlinx.coroutines.delay(500)
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                userProfile = UserProfile(
                    name = "Sophia Chen",
                    email = "sophia.chen@email.com",
                    avatarUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuB7xEQNenK5ptb_OO7dMLAipQ0yAbZdL2LfLVfEBIxr1Ns5MC9jE1bC-oJ3Ih4SeMfg1-znJ-2DU8b0H7qwk_jzM2emfQHcT0BlGAk0mmWvD7mYWj1nshUOloubjsrgH5DNSXiIj2selfZpSeKGkvWLQ8x5JAFdv8diBFL003r-piu_9MX5Jds7cDip0aSJzKF5mKVy-zNcn830z9nXydzL2-vvo2rlCD9F8TcGyLN1awX1k3bGkaFBf5CF531raUfkMKQ_nEDesIo"
                )
            )
        }
    }

    fun logout() {
        // Implement logout logic
    }
}
