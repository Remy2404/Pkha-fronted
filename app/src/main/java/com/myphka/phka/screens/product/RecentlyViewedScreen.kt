package com.myphka.phka.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

data class RecentlyViewedItem(
    val name: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentlyViewedScreen(
    navController: NavController
) {
    val recentlyViewedItems = listOf(
        RecentlyViewedItem("Radiant Glow Serum", "https://lh3.googleusercontent.com/aida-public/AB6AXuDu0J6QojFaIxECPHYG9o2eJY7rDPPZl1a7EoRBqJicTJseRoLVPFF0khxwpoLVQc6msnUy-ttGfFGJahrC3lkBIsgzuLngGVQGtfSgeTT8UMxx4wHb1jFXQ0Y7pKvwwQnqU19A3Swnh3UOEQrHNw3txGpOLDL-RqgxJtf9PyzmJ5YHp2ji8RVX3Yn9fR284CVj0EL4TRXhcwUcJFD72x3KB26gEkedz_C7dTJY7K3BcNmI1osv3_Y7DBaML_8tajm2S174-P_6qVo"),
        RecentlyViewedItem("Hydrating Lip Balm", "https://lh3.googleusercontent.com/aida-public/AB6AXuBfyARx8EYKFGAPg0vZJjllNBUw89X5sLGuj008MAtByXiodK0hAOEtGiKUAzmKEWk_ABOv_wjaSow9yHQ4xoN7qlZ8LBk_KB23ekpL2MG3TkUbzsZN_YW2b05LdDH-WzlSUkbL5KbRXq8QHFkPMvGzXZXl8fl2DW6eZUJapW1oAa-L6bLESE9dKcKP9YciHCaVjAVxZS9NQrqze_PpUQIQxVFloRbd-Bw1MC7-9PdGCJch-6ydBFAccPH7giTKSh86yOoOBiByylU"),
        RecentlyViewedItem("Soothing Eye Cream", "https://lh3.googleusercontent.com/aida-public/AB6AXuC4cm6xYytJHNQsKQuyzb6Bv6Xm2M0uedyuCiop3ygO2mB0BPXL1t7hUdAOL64iMcvySTScSll9sGzGFE-0DNc7a5aC1IlVtpPJ84AHoLTaHzT80GwbAsXr4ywLvy2JE8l-cmSYwiVLM7w1H3PDErnt44SGJZKLzCGD89dLZji8-qNRNNyzG_Y7x02fo20udrLA3h9GRMdhqGvGO8MQshu-K_Crl0aE4GGGWQqAs9EIPrTAwkGm3_zGHX1G-grTw3mYFlrqS5_cmGE"),
        RecentlyViewedItem("Matte Finish Foundation", "https://lh3.googleusercontent.com/aida-public/AB6AXuAWeqcrtF9qD92m8U8NUT6Iovlcl3MZZD82rrek7xDlWXhWowqWHJnqKQkw77Q3WE558ey60lVVJSoW67sGxLbWhgREDX340rQN7FLKsAaKjOaVS1NSfIj9gKFSHcekfLluyA1BcXJD5VZFoXErKkXQdFNStbLXS1ayB00vLy7omePUHltUQohMX5pC-Q_ycSb3TZSWhGQtXnQ7QX7SzRERZeg-Vzz4ZsaEA0DYtvUtoiJnpJ0vquY0MXICiXs1VU9K8xIkGtQofS4")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Recently Viewed", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = "Last 30 days",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(recentlyViewedItems) { item ->
                    RecentlyViewedProductItem(item)
                }
            }

            // Clear History Button
            Button(
                onClick = { /* Clear History */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(Icons.Default.Delete, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Clear History", fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun RecentlyViewedProductItem(item: RecentlyViewedItem) {
    Column {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            maxLines = 2
        )
    }
}
