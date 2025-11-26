package com.myphka.phka.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HowToUseScreen(
    navController: NavController,
    productId: String
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("How to use", fontWeight = FontWeight.Bold) },
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
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Video Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuA_brSE858oUr97viYBVQSXalWjAt_pY8vRaIibafiYlq0YLuDzGxpSTKu1CQDgtggiGgYXCdMpb4ToLu86sVnxsuqQrUH_g34WxJLm6PFNzJwhmEb6Qo77apOsHVdEN_uWH7v09ocXo_5oc8nfrVuo6x-RhMWGVtJIUUuD4KEKJqsp3TjvGu5PxruUptvgV7r2YBwcXaS9nLI4oJXcYw_t_CBlGixQ10h5InWmbGb-aMPIVdmwGB7DKrj_dC6KPbNnlPgHXVpSfKs",
                    contentDescription = "Video Thumbnail",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { /* Play Video */ },
                        modifier = Modifier
                            .size(64.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                    ) {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            // Tips & Tricks
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Tips & Tricks",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                TipItem(
                    icon = Icons.Default.Spa,
                    title = "Application",
                    description = "Apply a thin layer of the product to clean, dry skin, avoiding the eye area. Leave on for 10-15 minutes, then rinse thoroughly with lukewarm water."
                )
                Spacer(modifier = Modifier.height(16.dp))
                TipItem(
                    icon = Icons.Default.Schedule,
                    title = "Frequency",
                    description = "For best results, use 2-3 times a week. If you have sensitive skin, start with once a week and gradually increase frequency as tolerated."
                )
                Spacer(modifier = Modifier.height(16.dp))
                TipItem(
                    icon = Icons.Default.WaterDrop,
                    title = "Moisturizing",
                    description = "Follow with your favorite moisturizer to lock in hydration and keep your skin feeling soft and supple."
                )
            }
        }
    }
}

@Composable
fun TipItem(
    icon: ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 20.sp
            )
        }
    }
}
