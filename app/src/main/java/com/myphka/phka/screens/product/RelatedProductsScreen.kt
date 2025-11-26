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
fun RelatedProductsScreen(navController: NavController, productId: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Related Products Screen for $productId")
    }
}

@Preview
@Composable
fun RelatedProductsScreenPreview() {
    RelatedProductsScreen(navController = rememberNavController(), productId = "1")
}
