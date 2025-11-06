package com.myphka.phka.repositories

data class RegisterRequest(val name: String, val email: String, val password: String)
data class RegisterResponse(val token: String, val userId: String)

class RegisterRepository {
    suspend fun register(
        @Suppress("UNUSED_PARAMETER") name: String,
        @Suppress("UNUSED_PARAMETER") email: String,
        @Suppress("UNUSED_PARAMETER") password: String
    ): Result<RegisterResponse> {
        return try {
            // Placeholder for actual API call
            Result.success(RegisterResponse(token = "fake_token", userId = "user_123"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun checkEmailExists(@Suppress("UNUSED_PARAMETER") email: String): Result<Boolean> {
        return try {
            Result.success(false)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
