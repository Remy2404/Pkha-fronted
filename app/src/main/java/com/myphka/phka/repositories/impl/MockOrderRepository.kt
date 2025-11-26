package com.myphka.phka.repositories.impl

import com.myphka.phka.models.Order
import com.myphka.phka.models.OrderStatus
import com.myphka.phka.repositories.IOrderRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import java.util.Date
import javax.inject.Inject

class MockOrderRepository @Inject constructor() : IOrderRepository {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())

    override fun getOrderHistory(): Flow<List<Order>> = _orders

    override suspend fun getOrderById(orderId: String): Order? {
        return _orders.value.find { it.id == orderId }
    }

    override suspend fun placeOrder(order: Order): Result<String> {
        delay(1000) // Simulate network
        val newOrder = order.copy(id = "ORD-${System.currentTimeMillis()}", date = Date())
        _orders.value = _orders.value + newOrder
        return Result.success(newOrder.id)
    }

    override suspend fun trackOrder(orderId: String): Flow<Order> = flow {
        val order = _orders.value.find { it.id == orderId } ?: return@flow
        emit(order)
        delay(2000)
        emit(order.copy(status = OrderStatus.SHIPPED))
        delay(2000)
        emit(order.copy(status = OrderStatus.DELIVERED))
    }
}
