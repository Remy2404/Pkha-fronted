package com.myphka.phka.viewmodels.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.models.*
import com.myphka.phka.repositories.ICartRepository
import com.myphka.phka.repositories.IOrderRepository
import com.myphka.phka.repositories.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val cartRepository: ICartRepository,
    private val orderRepository: IOrderRepository,
    private val userRepository: IUserRepository
) : ViewModel() {

    // Cart Data
    val cartItems = cartRepository.cartItems.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    val subtotal = cartRepository.cartTotal.stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    // Checkout State
    private val _selectedAddress = MutableStateFlow<Address?>(null)
    val selectedAddress = _selectedAddress.asStateFlow()

    private val _selectedPaymentMethod = MutableStateFlow<PaymentMethod?>(null)
    val selectedPaymentMethod = _selectedPaymentMethod.asStateFlow()

    val savedAddresses = userRepository.savedAddresses.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Order Placement Result
    private val _orderPlacementResult = MutableStateFlow<Result<String>?>(null)
    val orderPlacementResult = _orderPlacementResult.asStateFlow()

    fun selectAddress(address: Address) {
        _selectedAddress.value = address
    }

    fun selectPaymentMethod(method: PaymentMethod) {
        _selectedPaymentMethod.value = method
    }

    fun placeOrder() {
        viewModelScope.launch {
            val items = cartItems.value
            val address = _selectedAddress.value
            val payment = _selectedPaymentMethod.value
            val sub = subtotal.value

            if (items.isEmpty() || address == null || payment == null) {
                _orderPlacementResult.value = Result.failure(Exception("Invalid checkout state"))
                return@launch
            }

            val orderItems = items.map { 
                OrderItem(
                    productId = it.product.id,
                    productName = it.product.name,
                    productImageRes = it.product.imageRes,
                    quantity = it.quantity,
                    price = it.product.price,
                    variants = it.selectedVariants.joinToString { v -> v.name }
                )
            }

            val shippingCost = 5.0 // Flat rate for now
            val tax = sub * 0.1 // 10% tax

            val order = Order(
                id = "", // Repo will assign
                date = Date(),
                status = OrderStatus.PROCESSING,
                items = orderItems,
                shippingAddress = address,
                paymentMethod = payment.name,
                subtotal = sub,
                shippingCost = shippingCost,
                tax = tax,
                total = sub + shippingCost + tax
            )

            val result = orderRepository.placeOrder(order)
            if (result.isSuccess) {
                cartRepository.clearCart()
            }
            _orderPlacementResult.value = result
        }
    }
    
    fun resetOrderState() {
        _orderPlacementResult.value = null
    }
}
