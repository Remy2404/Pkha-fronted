package com.myphka.phka.repositories

import com.myphka.phka.models.Order
import kotlinx.coroutines.flow.Flow

interface IOrderRepository {
    fun getOrderHistory(): Flow<List<Order>>
    suspend fun getOrderById(orderId: String): Order?
    suspend fun placeOrder(order: Order): Result<String> // Returns Order ID
    suspend fun trackOrder(orderId: String): Flow<Order>
}
