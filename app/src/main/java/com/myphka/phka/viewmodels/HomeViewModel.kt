package com.myphka.phka.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.repositories.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Banner(val id: String, val title: String, val imageUrl: String)
data class Category(val id: String, val name: String, val imageUrl: String)
data class Product(val id: String, val name: String, val price: Double, val imageUrl: String, val rating: Float = 0f)

data class HomeUiState(
    val banners: List<Banner> = emptyList(),
    val categories: List<Category> = emptyList(),
    val featuredProducts: List<Product> = emptyList(),
    val recommendedProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel(private val repository: HomeRepository = HomeRepository()) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                val banners = repository.getBanners()
                val categories = repository.getCategories()
                val featuredProducts = repository.getFeaturedProducts()
                val recommendedProducts = repository.getRecommendedProducts()

                _uiState.value = _uiState.value.copy(
                    banners = banners,
                    categories = categories,
                    featuredProducts = featuredProducts,
                    recommendedProducts = recommendedProducts,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load home data"
                )
            }
        }
    }

    fun refreshData() {
        loadHomeData()
    }
}