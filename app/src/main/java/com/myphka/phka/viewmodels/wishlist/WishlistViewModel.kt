package com.myphka.phka.viewmodels.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myphka.phka.models.Product
import com.myphka.phka.repositories.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val userRepository: IUserRepository
) : ViewModel() {

    val wishlist: StateFlow<List<Product>> = userRepository.wishlist
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun removeFromWishlist(productId: String) {
        viewModelScope.launch {
            userRepository.removeFromWishlist(productId)
        }
    }
}
