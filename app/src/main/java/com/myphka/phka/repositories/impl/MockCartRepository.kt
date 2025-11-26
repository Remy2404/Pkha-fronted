package com.myphka.phka.repositories.impl

import com.myphka.phka.models.CartItem
import com.myphka.phka.repositories.ICartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MockCartRepository @Inject constructor() : ICartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems: Flow<List<CartItem>> = _cartItems

    override val cartTotal: Flow<Double> = _cartItems.map { items ->
        items.sumOf { it.totalPrice }
    }

    override suspend fun addToCart(item: CartItem) {
        val currentItems = _cartItems.value.toMutableList()
        val existingIndex = currentItems.indexOfFirst { it.id == item.id }
        if (existingIndex != -1) {
            val existingItem = currentItems[existingIndex]
            currentItems[existingIndex] = existingItem.copy(quantity = existingItem.quantity + item.quantity)
        } else {
            currentItems.add(item)
        }
        _cartItems.value = currentItems
    }

    override suspend fun removeFromCart(itemId: String) {
        _cartItems.value = _cartItems.value.filter { it.id != itemId }
    }

    override suspend fun updateQuantity(itemId: String, quantity: Int) {
        if (quantity <= 0) {
            removeFromCart(itemId)
            return
        }
        _cartItems.value = _cartItems.value.map {
            if (it.id == itemId) it.copy(quantity = quantity) else it
        }
    }

    override suspend fun clearCart() {
        _cartItems.value = emptyList()
    }
}
