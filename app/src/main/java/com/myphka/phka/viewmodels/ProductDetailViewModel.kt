package com.myphka.phka.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.repositories.ProductDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductDetail(
    val id: String,
    val name: String,
    val price: Double,
    val originalPrice: Double? = null,
    val imageUrl: String? = null,
    val imageRes: Int,
    val rating: Float,
    val reviewCount: Int,
    val description: String,
    val isFavorite: Boolean = false,
    val category: String = "",
    val brand: String = "",
    val ingredients: List<String> = emptyList()
)

data class ProductDetailUiState(
    val product: ProductDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductDetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val productId: String = savedStateHandle["productId"] ?: ""

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState

    init {
        loadProductDetail()
    }

    private fun loadProductDetail() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                val product = repository.getProductById(productId)
                _uiState.value = _uiState.value.copy(
                    product = product,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load product details"
                )
            }
        }
    }

    fun toggleFavorite() {
        val currentProduct = _uiState.value.product ?: return
        val updatedProduct = currentProduct.copy(isFavorite = !currentProduct.isFavorite)
        _uiState.value = _uiState.value.copy(product = updatedProduct)

        // In a real app, you'd call repository to update favorite status
        viewModelScope.launch {
            try {
                repository.toggleFavorite(productId)
            } catch (e: Exception) {
                // Revert on error
                val revertedProduct = currentProduct.copy(isFavorite = !currentProduct.isFavorite)
                _uiState.value = _uiState.value.copy(product = revertedProduct)
            }
        }
    }

    fun refreshProduct() {
        loadProductDetail()
    }
}