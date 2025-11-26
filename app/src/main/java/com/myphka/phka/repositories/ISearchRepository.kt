package com.myphka.phka.repositories

import com.myphka.phka.models.FilterOptions
import com.myphka.phka.models.Product
import com.myphka.phka.models.SearchHistory
import com.myphka.phka.models.SortOption
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    fun getRecentSearches(): Flow<List<SearchHistory>>
    suspend fun addSearchToHistory(query: String)
    suspend fun clearSearchHistory()
    suspend fun removeSearchHistoryItem(id: String)
    
    suspend fun searchProducts(
        query: String,
        filters: FilterOptions? = null,
        sortOption: SortOption = SortOption.RELEVANCE
    ): List<Product>
}
