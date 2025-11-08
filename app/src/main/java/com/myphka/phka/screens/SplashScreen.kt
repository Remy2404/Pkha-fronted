package com.myphka.phka.screens

import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myphka.phka.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { mutableFloatStateOf(1f) }
    val animationSpec = infiniteRepeatable(
        animation = keyframes {
            durationMillis = 2000
            1f at 0
            1.1f at 1000
            1f at 2000
        }
    )

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate("onboarding_welcome") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Phka Logo",
                    modifier = Modifier
                        .size(64.dp)
                        .scale(scale.value),
                    tint = DeepPink
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Phka",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    color = DeepPink
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                color = DeepPink,
                strokeWidth = 4.dp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Loading, please wait...",
                fontSize = 18.sp,
                color = DeepPink
            )
        }
    }
}
