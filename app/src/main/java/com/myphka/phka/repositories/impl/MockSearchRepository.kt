package com.myphka.phka.repositories.impl

import com.myphka.phka.models.FilterOptions
import com.myphka.phka.models.Product
import com.myphka.phka.models.SearchHistory
import com.myphka.phka.models.SortOption
import com.myphka.phka.repositories.ISearchRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MockSearchRepository @Inject constructor() : ISearchRepository {
    private val _history = MutableStateFlow<List<SearchHistory>>(
        listOf(
            SearchHistory("1", "Lipstick", System.currentTimeMillis()),
            SearchHistory("2", "Foundation", System.currentTimeMillis() - 100000)
        )
    )

    override fun getRecentSearches(): Flow<List<SearchHistory>> = _history

    override suspend fun addSearchToHistory(query: String) {
        val newItem = SearchHistory(System.currentTimeMillis().toString(), query, System.currentTimeMillis())
        _history.value = listOf(newItem) + _history.value
    }

    override suspend fun clearSearchHistory() {
        _history.value = emptyList()
    }

    override suspend fun removeSearchHistoryItem(id: String) {
        _history.value = _history.value.filter { it.id != id }
    }

    override suspend fun searchProducts(
        query: String,
        filters: FilterOptions?,
        sortOption: SortOption
    ): List<Product> {
        delay(500)
        // Return dummy products for now
        return listOf(
            Product("1", "Phka Matte Lipstick", 25.0, 0, "Makeup"),
            Product("2", "Phka Glossy Lipstick", 20.0, 0, "Makeup"),
            Product("3", "Phka Vitamin C Serum", 30.0, 0, "Skincare")
        ).filter { it.name.contains(query, ignoreCase = true) }
    }
}
