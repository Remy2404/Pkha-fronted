package com.myphka.phka.repositories

import com.myphka.phka.models.Address
import com.myphka.phka.models.Product
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    val savedAddresses: Flow<List<Address>>
    val wishlist: Flow<List<Product>>

    suspend fun addAddress(address: Address)
    suspend fun updateAddress(address: Address)
    suspend fun deleteAddress(addressId: String)
    
    suspend fun addToWishlist(product: Product)
    suspend fun removeFromWishlist(productId: String)
    suspend fun isInWishlist(productId: String): Boolean
}
