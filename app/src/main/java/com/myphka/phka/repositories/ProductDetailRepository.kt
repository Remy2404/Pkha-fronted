package com.myphka.phka.repositories

import com.myphka.phka.viewmodels.ProductDetail

class ProductDetailRepository {
    suspend fun getProductById(productId: String): ProductDetail {
        // Placeholder for actual API call
        return when (productId) {
            "1" -> ProductDetail(
                id = "1",
                name = "Hydrating Face Cream",
                price = 29.99,
                originalPrice = 39.99,
                imageUrl = "cream1.jpg",
                rating = 4.5f,
                reviewCount = 128,
                description = "This luxurious hydrating face cream deeply nourishes and moisturizes your skin, leaving it soft, smooth, and radiant. Enriched with hyaluronic acid and vitamin E, it helps retain moisture throughout the day while protecting against environmental stressors.",
                isFavorite = false,
                category = "Skincare",
                brand = "GlowBeauty",
                ingredients = listOf("Water", "Hyaluronic Acid", "Vitamin E", "Glycerin", "Shea Butter")
            )
            "2" -> ProductDetail(
                id = "2",
                name = "Gentle Cleanser",
                price = 18.99,
                imageUrl = "cleanser1.jpg",
                rating = 4.2f,
                reviewCount = 89,
                description = "A gentle, sulfate-free cleanser that effectively removes impurities without stripping your skin's natural moisture barrier. Perfect for daily use on all skin types.",
                isFavorite = true,
                category = "Skincare",
                brand = "PureSkin",
                ingredients = listOf("Water", "Coconut Oil", "Aloe Vera", "Green Tea Extract")
            )
            else -> ProductDetail(
                id = productId,
                name = "Sample Product",
                price = 19.99,
                imageUrl = "sample.jpg",
                rating = 4.0f,
                reviewCount = 50,
                description = "A sample beauty product description.",
                category = "Beauty",
                brand = "Sample Brand"
            )
        }
    }

    suspend fun toggleFavorite(productId: String): Result<Unit> {
        // Placeholder for actual API call
        // reference productId so compiler doesn't mark it unused
        println("toggleFavorite called for: $productId")
        return Result.success(Unit)
    }
}