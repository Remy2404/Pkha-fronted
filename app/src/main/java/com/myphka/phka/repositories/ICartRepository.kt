package com.myphka.phka.repositories

import com.myphka.phka.models.CartItem
import kotlinx.coroutines.flow.Flow

interface ICartRepository {
    val cartItems: Flow<List<CartItem>>
    val cartTotal: Flow<Double>

    suspend fun addToCart(item: CartItem)
    suspend fun removeFromCart(itemId: String)
    suspend fun updateQuantity(itemId: String, quantity: Int)
    suspend fun clearCart()
}
