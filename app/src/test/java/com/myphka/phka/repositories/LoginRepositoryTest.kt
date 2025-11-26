package com.myphka.phka.repositories

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class LoginRepositoryTest {
    private val repository = LoginRepository()

    @Test
    fun login_returns_success_with_token_and_userId() {
        runBlocking {
            val result = repository.login("test@example.com", "password123")
            assertTrue("Result should be success", result.isSuccess)
            val response = result.getOrNull()
            assertNotNull("Response should not be null", response)
            assertEquals("fake_token", response?.token)
            assertEquals("user_123", response?.userId)
        }
    }

    @Test
    fun login_returns_failure_for_invalid_credentials() {
        runBlocking {
            val result = repository.login("wrong@example.com", "badpass")
            assertTrue("Result should be failure", result.isFailure)
            val exception = result.exceptionOrNull()
            assertNotNull("Exception should be present", exception)
            assertEquals("Invalid credentials", exception?.message)
        }
    }

    @Test
    fun loginWithGoogle_returns_success() {
        runBlocking {
            val result = repository.loginWithGoogle("fake_id_token")
            assertTrue("Result should be success", result.isSuccess)
            val response = result.getOrNull()
            assertNotNull(response)
            assertEquals("fake_token", response?.token)
            assertEquals("user_123", response?.userId)
        }
    }

    @Test
    fun loginWithApple_returns_success() {
        runBlocking {
            val result = repository.loginWithApple("fake_id_token")
            assertTrue("Result should be success", result.isSuccess)
            val response = result.getOrNull()
            assertNotNull(response)
            assertEquals("fake_token", response?.token)
            assertEquals("user_123", response?.userId)
        }
    }
}
