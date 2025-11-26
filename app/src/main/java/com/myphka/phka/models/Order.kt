package com.myphka.phka.models

import java.util.Date

data class Order(
    val id: String,
    val date: Date,
    val status: OrderStatus,
    val items: List<OrderItem>,
    val shippingAddress: Address,
    val paymentMethod: String, // Just the name for now
    val subtotal: Double,
    val shippingCost: Double,
    val tax: Double,
    val total: Double
)

data class OrderItem(
    val productId: String,
    val productName: String,
    val productImageRes: Int,
    val quantity: Int,
    val price: Double,
    val variants: String // "Size: M, Color: Red"
)

enum class OrderStatus {
    PROCESSING, SHIPPED, DELIVERED, CANCELLED
}
