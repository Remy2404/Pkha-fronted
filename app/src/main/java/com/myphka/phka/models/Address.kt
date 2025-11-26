package com.myphka.phka.models

data class Address(
    val id: String,
    val name: String, // e.g., "Home", "Office"
    val recipientName: String,
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val phoneNumber: String,
    val isDefault: Boolean = false
)
