package com.myphka.phka.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CategoryUiState(val categories: List<String> = emptyList())

class OnboardingCategoriesViewModel : ViewModel() {
    private val _currentStep = MutableStateFlow(2)
    val currentStep: StateFlow<Int> = _currentStep

    private val _categories = MutableStateFlow(
        listOf("Foundation", "Concealer", "Setting Powder")
    )
    val categories: StateFlow<List<String>> = _categories

    fun nextStep() {
        _currentStep.value = 3
    }

    fun skipOnboarding() {
        // Navigate to login
    }
}
