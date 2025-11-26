package com.myphka.phka.models

import androidx.annotation.DrawableRes

sealed class PaymentMethod(val id: String, val name: String, @DrawableRes val iconRes: Int? = null) {
    data class CreditCard(
        val last4Digits: String,
        val cardHolderName: String,
        val expiryDate: String,
        val cardType: String // Visa, Mastercard
    ) : PaymentMethod("cc_${last4Digits}", "Credit Card", null)

    object PayPal : PaymentMethod("paypal", "PayPal", null)
    object COD : PaymentMethod("cod", "Cash on Delivery", null)
}
