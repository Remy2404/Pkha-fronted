package com.myphka.phka.repositories

import com.myphka.phka.viewmodels.Review
import javax.inject.Inject

class ProductReviewsRepository @Inject constructor() {
    suspend fun getReviewsByProductId(productId: String): List<Review> {
        // Placeholder for actual API call
        return when (productId) {
            "1" -> listOf(
                Review(
                    id = "1",
                    userName = "Sarah Johnson",
                    rating = 5,
                    comment = "This cream is amazing! My skin feels so hydrated and looks much more radiant. I've been using it for 2 weeks and already see a big difference. Highly recommend!",
                    date = "2024-01-15",
                    images = listOf("review1.jpg")
                ),
                Review(
                    id = "2",
                    userName = "Mike Chen",
                    rating = 4,
                    comment = "Good product overall. Absorbs well and doesn't leave a greasy feeling. The scent is pleasant too. Would buy again.",
                    date = "2024-01-10"
                ),
                Review(
                    id = "3",
                    userName = "Emma Davis",
                    rating = 5,
                    comment = "Love this cream! It's lightweight but provides great moisture. Perfect for my dry skin. The packaging is also very elegant.",
                    date = "2024-01-08",
                    images = listOf("review2.jpg", "review3.jpg")
                ),
                Review(
                    id = "4",
                    userName = "Alex Rodriguez",
                    rating = 4,
                    comment = "Effective moisturizer. Takes a bit to absorb but the results are worth it. Good value for money.",
                    date = "2024-01-05"
                ),
                Review(
                    id = "5",
                    userName = "Lisa Wang",
                    rating = 3,
                    comment = "It's okay, does the job but nothing special. Expected more from the price point.",
                    date = "2024-01-02"
                )
            )
            else -> listOf(
                Review(
                    id = "sample1",
                    userName = "Sample User",
                    rating = 4,
                    comment = "Sample review text for this product.",
                    date = "2024-01-01"
                )
            )
        }
    }
}