package com.myphka.phka.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class FeatureUiState(val title: String = "", val description: String = "", val icon: String = "")

class OnboardingFeaturesViewModel : ViewModel() {
    private val _currentStep = MutableStateFlow(3)
    val currentStep: StateFlow<Int> = _currentStep

    private val _features = MutableStateFlow(
        listOf(
            FeatureUiState("AR Try-On", "Virtually try on makeup products before you buy.", "build"),
            FeatureUiState("Fast Delivery", "Get your beauty essentials delivered quickly and reliably.", "local_shipping"),
            FeatureUiState("Secure Payment", "Shop with confidence knowing your transactions are secure.", "lock")
        )
    )
    val features: StateFlow<List<FeatureUiState>> = _features

    fun completeOnboarding() {
        // Navigate to home
    }
}
