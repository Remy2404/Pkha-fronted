package com.myphka.phka.models

data class CartItem(
    val id: String,
    val product: Product,
    val selectedVariants: List<ProductVariant> = emptyList(),
    val quantity: Int
) {
    val totalPrice: Double
        get() = (product.price + selectedVariants.sumOf { it.priceModifier }) * quantity
}
