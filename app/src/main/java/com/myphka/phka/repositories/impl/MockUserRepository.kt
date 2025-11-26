package com.myphka.phka.repositories.impl

import com.myphka.phka.models.Address
import com.myphka.phka.models.Product
import com.myphka.phka.repositories.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MockUserRepository @Inject constructor() : IUserRepository {
    private val _addresses = MutableStateFlow<List<Address>>(
        listOf(
            Address("1", "Home", "User Name", "123 Main St", "Phnom Penh", "PP", "12000", "012345678", true)
        )
    )
    private val _wishlist = MutableStateFlow<List<Product>>(emptyList())

    override val savedAddresses: Flow<List<Address>> = _addresses
    override val wishlist: Flow<List<Product>> = _wishlist

    override suspend fun addAddress(address: Address) {
        _addresses.value = _addresses.value + address
    }

    override suspend fun updateAddress(address: Address) {
        _addresses.value = _addresses.value.map { if (it.id == address.id) address else it }
    }

    override suspend fun deleteAddress(addressId: String) {
        _addresses.value = _addresses.value.filter { it.id != addressId }
    }

    override suspend fun addToWishlist(product: Product) {
        if (_wishlist.value.none { it.id == product.id }) {
            _wishlist.value = _wishlist.value + product
        }
    }

    override suspend fun removeFromWishlist(productId: String) {
        _wishlist.value = _wishlist.value.filter { it.id != productId }
    }

    override suspend fun isInWishlist(productId: String): Boolean {
        return _wishlist.value.any { it.id == productId }
    }
}
