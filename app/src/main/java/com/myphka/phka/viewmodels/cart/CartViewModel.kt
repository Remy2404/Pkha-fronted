package com.myphka.phka.viewmodels.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.models.CartItem
import com.myphka.phka.repositories.ICartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: ICartRepository
) : ViewModel() {

    val cartItems: StateFlow<List<CartItem>> = cartRepository.cartItems
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val cartTotal: StateFlow<Double> = cartRepository.cartTotal
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    fun removeFromCart(itemId: String) {
        viewModelScope.launch {
            cartRepository.removeFromCart(itemId)
        }
    }

    fun updateQuantity(itemId: String, quantity: Int) {
        viewModelScope.launch {
            cartRepository.updateQuantity(itemId, quantity)
        }
    }
}
