package com.myphka.phka.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.repositories.ProductListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProductListItem(
    val id: String,
    val name: String,
    val price: Double,
    val originalPrice: Double? = null,
    val imageUrl: String,
    val rating: Float,
    val reviewCount: Int,
    val isFavorite: Boolean = false
)

data class ProductListUiState(
    val categoryName: String? = null,
    val products: List<ProductListItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ProductListViewModel(
    private val repository: ProductListRepository = ProductListRepository(),
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val categoryId: String = savedStateHandle["categoryId"] ?: ""

    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                val category = repository.getCategoryById(categoryId)
                val products = repository.getProductsByCategory(categoryId)

                _uiState.value = _uiState.value.copy(
                    categoryName = category?.name,
                    products = products,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load products"
                )
            }
        }
    }

    fun toggleFavorite(productId: String) {
        val updatedProducts = _uiState.value.products.map { product ->
            if (product.id == productId) {
                product.copy(isFavorite = !product.isFavorite)
            } else {
                product
            }
        }
        _uiState.value = _uiState.value.copy(products = updatedProducts)

        // In a real app, you'd call repository to update favorite status
        viewModelScope.launch {
            try {
                repository.toggleFavorite(productId)
            } catch (e: Exception) {
                // Revert on error
                val revertedProducts = _uiState.value.products.map { product ->
                    if (product.id == productId) {
                        product.copy(isFavorite = !product.isFavorite)
                    } else {
                        product
                    }
                }
                _uiState.value = _uiState.value.copy(products = revertedProducts)
            }
        }
    }

    fun refreshProducts() {
        loadProducts()
    }
}