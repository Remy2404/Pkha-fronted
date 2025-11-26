package com.myphka.phka.screens.tryon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun VirtualTryOnScreen(
    navController: NavController
) {
    var selectedShadeIndex by remember { mutableIntStateOf(2) }
    val shades = listOf(
        Color(0xFFF2E6D9),
        Color(0xFFE6D9CC),
        Color(0xFFD9CCBF),
        Color(0xFFCCBFA2),
        Color(0xFFBFA285),
        Color(0xFFA28568)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Camera Preview Placeholder
        AsyncImage(
            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBxPMK2LFG9obwSbZ7lPD9dB5b92JOk7rMOiRTI36ipg1AZYfFnfI1zQmT1y2Hn-W764WnLplRU7xkcUM2MDuDMvIkE_mKp7RV2YFhRUwgxQ6R08Qipd9zrzcLeNdbtiqefpYIjLrCbtUPofehRugFUQOH91r1RtMydn--zAl4FUN_dX2RL5MPplfy9ArkOKAM0FaMWU3i8AUlkTR2wlDtssfokGINPYopjJo8wdPGla8v16msJZe2wX2D5StYdt1t1X9fSkyubzf0",
            contentDescription = "Camera Preview",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Close Button
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(Color.Black.copy(alpha = 0.3f), CircleShape)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
        }

        // Face Guide Overlay
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(width = 256.dp, height = 320.dp)
                .border(2.dp, Color.White.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
        )

        // Controls
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            // Action Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /* Switch Camera */ },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Black.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(Icons.Default.Cameraswitch, contentDescription = "Switch Camera", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(24.dp))
                IconButton(
                    onClick = { /* Capture */ },
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(Icons.Default.Face, contentDescription = "Capture", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp))
                }
                Spacer(modifier = Modifier.width(24.dp))
                IconButton(
                    onClick = { /* Gallery */ },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Black.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(Icons.Default.Face, contentDescription = "Gallery", tint = Color.White) // Placeholder icon
                }
            }

            // Shade Selector
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .background(Color.Gray.copy(alpha = 0.5f), CircleShape)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Find your shade",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Select a shade to try on virtually",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    shades.forEachIndexed { index, color ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = if (selectedShadeIndex == index) 3.dp else 0.dp,
                                    color = if (selectedShadeIndex == index) MaterialTheme.colorScheme.primary else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable { selectedShadeIndex = index }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Try other products */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Try other products", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
