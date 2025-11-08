package com.myphka.phka.repositories

import com.myphka.phka.viewmodels.Banner
import com.myphka.phka.viewmodels.Category
import com.myphka.phka.viewmodels.Product

class HomeRepository {
    suspend fun getBanners(): List<Banner> {
        // Placeholder for actual API call
        return listOf(
            Banner("1", "New Arrivals", "banner1.jpg"),
            Banner("2", "Summer Sale", "banner2.jpg"),
            Banner("3", "Beauty Tips", "banner3.jpg")
        )
    }

    suspend fun getCategories(): List<Category> {
        // Placeholder for actual API call
        return listOf(
            Category("1", "Skincare", "skincare.jpg"),
            Category("2", "Makeup", "makeup.jpg"),
            Category("3", "Hair Care", "haircare.jpg"),
            Category("4", "Fragrance", "fragrance.jpg"),
            Category("5", "Tools", "tools.jpg")
        )
    }

    suspend fun getFeaturedProducts(): List<Product> {
        // Placeholder for actual API call
        return listOf(
            Product("1", "Hydrating Face Cream", 29.99, "cream1.jpg", 4.5f),
            Product("2", "Matte Lipstick", 15.99, "lipstick1.jpg", 4.2f),
            Product("3", "Shampoo Bar", 12.99, "shampoo1.jpg", 4.7f),
            Product("4", "Foundation", 35.99, "foundation1.jpg", 4.3f)
        )
    }

    suspend fun getRecommendedProducts(): List<Product> {
        // Placeholder for actual API call
        return listOf(
            Product("5", "Eye Shadow Palette", 45.99, "palette1.jpg", 4.6f),
            Product("6", "Facial Cleanser", 18.99, "cleanser1.jpg", 4.4f),
            Product("7", "Hair Mask", 22.99, "mask1.jpg", 4.8f),
            Product("8", "Mascara", 11.99, "mascara1.jpg", 4.1f),
            Product("9", "Body Lotion", 16.99, "lotion1.jpg", 4.3f),
            Product("10", "Sunscreen", 25.99, "sunscreen1.jpg", 4.5f)
        )
    }
}