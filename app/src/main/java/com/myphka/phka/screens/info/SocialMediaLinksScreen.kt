package com.myphka.phka.screens.info

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class SocialPlatform(
    val name: String,
    val handle: String,
    val url: String,
    val color: androidx.compose.ui.graphics.Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialMediaLinksScreen(navController: NavController) {
    val platforms = listOf(
        SocialPlatform("Facebook", "@PhkaBeauty", "https://facebook.com/phka", androidx.compose.ui.graphics.Color(0xFF1877F2)),
        SocialPlatform("Instagram", "@phka_beauty", "https://instagram.com/phka", androidx.compose.ui.graphics.Color(0xFFE4405F)),
        SocialPlatform("Twitter", "@PhkaBeauty", "https://twitter.com/phka", androidx.compose.ui.graphics.Color(0xFF1DA1F2)),
        SocialPlatform("TikTok", "@phkabeauty", "https://tiktok.com/@phka", androidx.compose.ui.graphics.Color(0xFF000000)),
        SocialPlatform("YouTube", "Phka Beauty", "https://youtube.com/phka", androidx.compose.ui.graphics.Color(0xFFFF0000))
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Follow Us", fontWeight = FontWeight.Bold) },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    "Connect with us on social media for the latest updates, beauty tips, and exclusive offers!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            items(platforms) { platform ->
                SocialPlatformCard(platform = platform)
            }
        }
    }
}

@Composable
fun SocialPlatformCard(platform: SocialPlatform) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Open link */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = platform.color.copy(alpha = 0.1f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        platform.name.first().toString(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = platform.color
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(platform.name, fontWeight = FontWeight.Bold)
                Text(
                    platform.handle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SocialMediaLinksScreenPreview() {
    MaterialTheme {
        SocialMediaLinksScreen(navController = rememberNavController())
    }
}
