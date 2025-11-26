package com.myphka.phka.viewmodels

import androidx.lifecycle.ViewModel
import com.myphka.phka.models.Banner
import com.myphka.phka.models.Category
import com.myphka.phka.models.Product
import com.myphka.phka.repositories.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val banners: List<Banner> = emptyList(),
    val categories: List<Category> = emptyList(),
    val featuredProducts: List<Product> = emptyList(),
    val recommendedProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel(
    private val repository: HomeRepository = HomeRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        try {
            _uiState.value = HomeUiState(
                banners = repository.getBanners(),
                categories = repository.getCategories(),
                featuredProducts = repository.getFeaturedProducts(),
                recommendedProducts = repository.getRecommendedProducts(),
                isLoading = false
            )
        } catch (e: Exception) {
            _uiState.value = HomeUiState(error = e.message)
        }
    }

    fun onCategoryClick(categoryId: String) {
    }

    fun onProductClick(productId: String) {
    }

    fun onBannerClick(bannerId: String) {
    }

    fun onSearchClick() {
    }

    fun onCartClick() {
    }
}

