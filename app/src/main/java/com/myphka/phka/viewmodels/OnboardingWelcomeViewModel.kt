package com.myphka.phka.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnboardingWelcomeViewModel : ViewModel() {
    private val _currentStep = MutableStateFlow(1)
    val currentStep: StateFlow<Int> = _currentStep

    fun nextStep() {
        _currentStep.value = 2
    }

    fun skipOnboarding() {
        // Navigate to login
    }
}
