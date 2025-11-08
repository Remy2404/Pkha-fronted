package com.myphka.phka.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.repositories.CategoryListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CategoryListItem(
    val id: String,
    val name: String,
    val imageUrl: String,
    val productCount: Int
)

data class CategoryListUiState(
    val categories: List<CategoryListItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class CategoryListViewModel(private val repository: CategoryListRepository = CategoryListRepository()) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoryListUiState())
    val uiState: StateFlow<CategoryListUiState> = _uiState

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                val categories = repository.getCategories()
                _uiState.value = _uiState.value.copy(
                    categories = categories,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load categories"
                )
            }
        }
    }

    fun refreshCategories() {
        loadCategories()
    }
}