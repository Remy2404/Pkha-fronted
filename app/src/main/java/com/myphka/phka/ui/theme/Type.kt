package com.myphka.phka.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.myphka.phka.R

val ArchivoBlack = FontFamily(Font(R.font.archivo_black_regular))

val PlayfairDisplay = FontFamily(
    Font(R.font.playfair_display_variable_font_wght),
    Font(R.font.playfair_display_italic_variable_font_wght, style = FontStyle.Italic)
)

val Roboto = FontFamily(
    Font(R.font.roboto_variable_font_wdth_wght),
    Font(R.font.roboto_italic_variable_font_wdth_wght, style = FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
