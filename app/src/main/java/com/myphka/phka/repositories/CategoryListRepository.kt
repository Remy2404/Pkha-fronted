package com.myphka.phka.repositories

import com.myphka.phka.viewmodels.CategoryListItem

class CategoryListRepository {
    suspend fun getCategories(): List<CategoryListItem> {
        // Placeholder for actual API call
        return listOf(
            CategoryListItem("1", "Skincare", "skincare.jpg", 245),
            CategoryListItem("2", "Makeup", "makeup.jpg", 189),
            CategoryListItem("3", "Hair Care", "haircare.jpg", 156),
            CategoryListItem("4", "Fragrance", "fragrance.jpg", 98),
            CategoryListItem("5", "Tools & Brushes", "tools.jpg", 67),
            CategoryListItem("6", "Nails", "nails.jpg", 43),
            CategoryListItem("7", "Body Care", "bodycare.jpg", 134),
            CategoryListItem("8", "Wellness", "wellness.jpg", 78)
        )
    }
}