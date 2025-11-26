package com.myphka.phka.repositories

import com.myphka.phka.R
import com.myphka.phka.models.Banner
import com.myphka.phka.models.Category
import com.myphka.phka.models.Product

class HomeRepository {
    fun getBanners(): List<Banner> = listOf(
        Banner(
            id = "1",
            title = "New Arrivals",
            imageRes = R.drawable.product_001
        ),
        Banner(
            id = "2",
            title = "Summer Sale",
            imageRes = R.drawable.product_002
        ),
        Banner(
            id = "3",
            title = "Haircare Essentials",
            imageRes = R.drawable.category_003
        )
    )

    fun getCategories(): List<Category> = listOf(
        Category(
            id = "1",
            name = "Makeup",
            imageRes = R.drawable.category_001
        ),
        Category(
            id = "2",
            name = "Skincare",
            imageRes = R.drawable.category_002
        ),
        Category(
            id = "3",
            name = "Haircare",
            imageRes = R.drawable.category_003
        ),
        Category(
            id = "4",
            name = "Fragrances",
            imageRes = R.drawable.category_004
        )
    )

    fun getFeaturedProducts(): List<Product> = listOf(
        Product(
            id = "1",
            name = "Luxury Lipstick",
            price = 25.0,
            imageRes = R.drawable.product_001,
            category = "Makeup"
        ),
        Product(
            id = "2",
            name = "Organic Face Serum",
            price = 45.0,
            imageRes = R.drawable.product_002,
            category = "Skincare"
        ),
        Product(
            id = "3",
            name = "Professional Hair Dryer",
            price = 80.0,
            imageRes = R.drawable.product_003,
            category = "Haircare"
        ),
        Product(
            id = "4",
            name = "Hydrating Face Mask",
            price = 15.0,
            imageRes = R.drawable.product_004,
            category = "Skincare"
        )
    )

    fun getRecommendedProducts(): List<Product> = listOf(
        Product(
            id = "5",
            name = "Hydrating Face Mask",
            price = 15.0,
            imageRes = R.drawable.product_005,
            category = "Skincare"
        ),
        Product(
            id = "6",
            name = "Anti-Aging Eye Cream",
            price = 35.0,
            imageRes = R.drawable.product_006,
            category = "Skincare"
        ),
        Product(
            id = "7",
            name = "Volumizing Hair Spray",
            price = 20.0,
            imageRes = R.drawable.product_007,
            category = "Haircare",
        )
    )
}

