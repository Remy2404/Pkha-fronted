package com.myphka.phka.repositories

import com.myphka.phka.viewmodels.CategoryListItem
import com.myphka.phka.viewmodels.ProductListItem

import javax.inject.Inject

class ProductListRepository @Inject constructor() {
    suspend fun getCategoryById(categoryId: String): CategoryListItem? {
        // Placeholder for actual API call
        return when (categoryId) {
            "1" -> CategoryListItem("1", "Skincare", "skincare.jpg", 245)
            "2" -> CategoryListItem("2", "Makeup", "makeup.jpg", 189)
            "3" -> CategoryListItem("3", "Hair Care", "haircare.jpg", 156)
            "4" -> CategoryListItem("4", "Fragrance", "fragrance.jpg", 98)
            "5" -> CategoryListItem("5", "Tools & Brushes", "tools.jpg", 67)
            else -> null
        }
    }

    suspend fun getProductsByCategory(categoryId: String): List<ProductListItem> {
        // Placeholder for actual API call
        return when (categoryId) {
            "1" -> listOf( // Skincare
                ProductListItem("1", "Hydrating Face Cream", 29.99, 39.99, "cream1.jpg", com.myphka.phka.R.drawable.product_001, 4.5f, 128, false),
                ProductListItem("2", "Gentle Cleanser", 18.99, null, "cleanser1.jpg", com.myphka.phka.R.drawable.product_001, 4.2f, 89, true),
                ProductListItem("3", "Vitamin C Serum", 45.99, 55.99, "serum1.jpg", com.myphka.phka.R.drawable.product_001, 4.7f, 203, false),
                ProductListItem("4", "Eye Cream", 32.99, null, "cream2.jpg", com.myphka.phka.R.drawable.product_001, 4.3f, 156, false),
                ProductListItem("5", "Moisturizing Mask", 12.99, null, "mask1.jpg", com.myphka.phka.R.drawable.product_001, 4.1f, 67, true),
                ProductListItem("6", "SPF 50 Sunscreen", 25.99, 35.99, "sunscreen1.jpg", com.myphka.phka.R.drawable.product_001, 4.6f, 178, false)
            )
            "2" -> listOf( // Makeup
                ProductListItem("7", "Matte Lipstick", 15.99, null, "lipstick1.jpg", com.myphka.phka.R.drawable.product_001, 4.2f, 145, false),
                ProductListItem("8", "Foundation", 35.99, 45.99, "foundation1.jpg", com.myphka.phka.R.drawable.product_001, 4.3f, 98, true),
                ProductListItem("9", "Mascara", 11.99, null, "mascara1.jpg", com.myphka.phka.R.drawable.product_001, 4.1f, 234, false),
                ProductListItem("10", "Eyeshadow Palette", 45.99, 55.99, "palette1.jpg", com.myphka.phka.R.drawable.product_001, 4.6f, 167, true)
            )
            else -> emptyList()
        }
    }

    suspend fun toggleFavorite(productId: String): Result<Unit> {
        // Placeholder for actual API call
        return Result.success(Unit)
    }
}