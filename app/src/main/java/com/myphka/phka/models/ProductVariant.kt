package com.myphka.phka.models

data class ProductVariant(
    val id: String,
    val name: String, // e.g., "Small", "Red"
    val type: VariantType, // SIZE, COLOR
    val priceModifier: Double = 0.0,
    val inStock: Boolean = true
)

enum class VariantType {
    SIZE, COLOR
}
