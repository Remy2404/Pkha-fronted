package com.myphka.phka.viewmodels.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.models.FilterOptions
import com.myphka.phka.models.Product
import com.myphka.phka.models.SearchHistory
import com.myphka.phka.models.SortOption
import com.myphka.phka.repositories.ISearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: ISearchRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Product>>(emptyList())
    val searchResults: StateFlow<List<Product>> = _searchResults.asStateFlow()

    private val _recentSearches = MutableStateFlow<List<SearchHistory>>(emptyList())
    val recentSearches: StateFlow<List<SearchHistory>> = _recentSearches.asStateFlow()

    private val _activeFilters = MutableStateFlow(FilterOptions())
    val activeFilters: StateFlow<FilterOptions> = _activeFilters.asStateFlow()

    private val _activeSort = MutableStateFlow(SortOption.RELEVANCE)
    val activeSort: StateFlow<SortOption> = _activeSort.asStateFlow()

    init {
        viewModelScope.launch {
            searchRepository.getRecentSearches().collectLatest {
                _recentSearches.value = it
            }
        }
    }

    fun onQueryChanged(query: String) {
        _searchQuery.value = query
        // Optional: Real-time search or wait for submit
        if (query.isNotEmpty()) {
             performSearch()
        }
    }

    fun onSearchTriggered(query: String) {
        viewModelScope.launch {
            searchRepository.addSearchToHistory(query)
            performSearch()
        }
    }

    fun onClearHistory() {
        viewModelScope.launch {
            searchRepository.clearSearchHistory()
        }
    }
    
    fun onRemoveHistoryItem(id: String) {
        viewModelScope.launch {
            searchRepository.removeSearchHistoryItem(id)
        }
    }

    fun updateFilters(filters: FilterOptions) {
        _activeFilters.value = filters
        performSearch()
    }

    fun updateSort(sortOption: SortOption) {
        _activeSort.value = sortOption
        performSearch()
    }

    private fun performSearch() {
        viewModelScope.launch {
            val results = searchRepository.searchProducts(
                _searchQuery.value,
                _activeFilters.value,
                _activeSort.value
            )
            _searchResults.value = results
        }
    }
}
