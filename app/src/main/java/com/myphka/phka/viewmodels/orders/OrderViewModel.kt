package com.myphka.phka.viewmodels.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.models.Order
import com.myphka.phka.repositories.IOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: IOrderRepository
) : ViewModel() {

    val orderHistory: StateFlow<List<Order>> = orderRepository.getOrderHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _currentOrder = MutableStateFlow<Order?>(null)
    val currentOrder: StateFlow<Order?> = _currentOrder.asStateFlow()

    fun loadOrderDetails(orderId: String) {
        viewModelScope.launch {
            _currentOrder.value = orderRepository.getOrderById(orderId)
        }
    }

    // Returns a flow that updates as the order status changes
    fun trackOrder(orderId: String): StateFlow<Order?> {
        val flow = MutableStateFlow<Order?>(null)
        viewModelScope.launch {
            orderRepository.trackOrder(orderId).collect {
                flow.value = it
            }
        }
        return flow.asStateFlow()
    }
}
