package com.myphka.phka.viewmodels.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class SettingsUiState(
    val notificationsEnabled: Boolean = true,
    val language: String = "English",
    val currency: String = "USD",
    val darkModeEnabled: Boolean = false,
    val theme: String = "Default" // Default, Pink, Green
)

class SettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState

    fun toggleNotifications(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(notificationsEnabled = enabled)
    }

    fun toggleDarkMode(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(darkModeEnabled = enabled)
    }

    fun setTheme(theme: String) {
        _uiState.value = _uiState.value.copy(theme = theme)
    }

    fun clearCache() {
        // Implement clear cache logic
    }
}
