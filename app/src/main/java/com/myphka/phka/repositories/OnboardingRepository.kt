package com.myphka.phka.repositories

class OnboardingRepository {
    suspend fun getOnboardingSteps(): List<String> {
        return listOf(
            "Welcome",
            "Categories",
            "Features"
        )
    }

    suspend fun completeOnboarding(userId: String) {
        // Mark onboarding as complete for user
    }
}
