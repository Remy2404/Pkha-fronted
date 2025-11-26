package com.myphka.phka.repositories

import android.util.Log
data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val userId: String)

class LoginRepository {
    suspend fun login(
        @Suppress("UNUSED_PARAMETER") email: String,
        @Suppress("UNUSED_PARAMETER") password: String
    ): Result<LoginResponse> {
        return try {
            Log.d("LoginRepository", "Attempting login for email: $email")
            // For testing: only accept the specific test credentials
            if (email == "test@x.com" && password == "123") {
                Result.success(LoginResponse(token = "fake_token", userId = "user_123"))
            } else {
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginWithGoogle(@Suppress("UNUSED_PARAMETER") idToken: String): Result<LoginResponse> {
        return try {
            Result.success(LoginResponse(token = "fake_token", userId = "user_123"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginWithApple(@Suppress("UNUSED_PARAMETER") idToken: String): Result<LoginResponse> {
        return try {
            Result.success(LoginResponse(token = "fake_token", userId = "user_123"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
