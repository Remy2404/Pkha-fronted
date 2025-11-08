package com.myphka.phka.repositories

data class ForgotPasswordRequest(val email: String)
data class ForgotPasswordResponse(val success: Boolean, val message: String)

class ForgotPasswordRepository {
    suspend fun sendResetLink(email: String): Result<ForgotPasswordResponse> {
        return try {
            // Placeholder for actual API call
            // Simulate network delay
            kotlinx.coroutines.delay(1000)
            Result.success(ForgotPasswordResponse(success = true, message = "Reset link sent"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}