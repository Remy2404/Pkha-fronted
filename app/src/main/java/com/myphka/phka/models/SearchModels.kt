package com.myphka.phka.models

data class SearchHistory(
    val id: String,
    val query: String,
    val timestamp: Long
)

data class FilterOptions(
    val priceRange: ClosedFloatingPointRange<Float>? = null,
    val brands: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val rating: Int? = null
)

enum class SortOption(val displayName: String) {
    RELEVANCE("Relevance"),
    PRICE_LOW_TO_HIGH("Price: Low to High"),
    PRICE_HIGH_TO_LOW("Price: High to Low"),
    NEWEST("Newest"),
    RATING("Top Rated")
}
