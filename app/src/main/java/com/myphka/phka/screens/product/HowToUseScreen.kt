package com.myphka.phka.screens.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun HowToUseScreen(navController: NavController, productId: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "How To Use Screen for $productId")
    }
}

@Preview
@Composable
fun HowToUseScreenPreview() {
    HowToUseScreen(navController = rememberNavController(), productId = "1")
}
