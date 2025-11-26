package com.myphka.phka.models

import androidx.annotation.DrawableRes

data class Banner(
    val id: String,
    val title: String,
    @DrawableRes val imageRes: Int,
    val imageUrl: String? = null
)

