package com.myphka.phka.models
import androidx.annotation.DrawableRes
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    @DrawableRes val imageRes: Int,
    val category: String = ""
)
