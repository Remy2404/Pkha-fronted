package com.myphka.phka.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.repositories.ProductReviewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Review(
    val id: String,
    val userName: String,
    val rating: Int,
    val comment: String,
    val date: String,
    val images: List<String> = emptyList()
)

data class ProductReviewsUiState(
    val averageRating: Float = 0f,
    val totalReviews: Int = 0,
    val reviews: List<Review> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ProductReviewsViewModel(
    private val repository: ProductReviewsRepository = ProductReviewsRepository(),
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val productId: String = savedStateHandle["productId"] ?: ""

    private val _uiState = MutableStateFlow(ProductReviewsUiState())
    val uiState: StateFlow<ProductReviewsUiState> = _uiState

    init {
        loadReviews()
    }

    private fun loadReviews() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                val reviews = repository.getReviewsByProductId(productId)
                val averageRating = if (reviews.isNotEmpty()) {
                    reviews.map { it.rating.toFloat() }.average().toFloat()
                } else {
                    0f
                }

                _uiState.value = _uiState.value.copy(
                    averageRating = averageRating,
                    totalReviews = reviews.size,
                    reviews = reviews,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load reviews"
                )
            }
        }
    }

    fun refreshReviews() {
        loadReviews()
    }
}