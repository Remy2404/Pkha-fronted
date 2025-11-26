package com.myphka.phka.models

import androidx.annotation.DrawableRes

data class Category(
    val id: String,
    val name: String,
    @DrawableRes val imageRes: Int,
    val imageUrl: String? = null
)

