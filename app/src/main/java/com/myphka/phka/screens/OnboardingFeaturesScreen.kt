package com.myphka.phka.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myphka.phka.R

sealed class FeatureIcon {
    data class Vector(val icon: ImageVector) : FeatureIcon()
    data class PainterIcon(val painter: Painter) : FeatureIcon()
}

data class Feature(val title: String, val description: String, val icon: FeatureIcon)

@Composable
fun OnboardingFeaturesScreen(navController: NavController) {
    val features = listOf(
        Feature(
            "AR Try-On",
            "Virtually try on makeup products before you buy.",
            FeatureIcon.Vector(Icons.Default.Build)
        ),
        Feature(
            "Fast Delivery",
            "Get your beauty essentials delivered quickly and reliably.",
            FeatureIcon.PainterIcon(painterResource(id = R.drawable.icons_local_shipping))
        ),
        Feature(
            "Secure Payment",
            "Shop with confidence knowing your transactions are secure.",
            FeatureIcon.Vector(Icons.Default.Lock)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F6F7))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            if (index == 2) Color(0xFFEC1380) else Color(0xFFEC1380).copy(alpha = 0.2f),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(50)
                        )
                )
                if (index < 2) Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Text(
            text = "Discover the best of Phka",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B0D14),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            features.forEach { feature ->
                FeatureCard(feature)
            }
        }

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEC1380)
            )
        ) {
            Text(
                text = "Get Started",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun FeatureCard(feature: Feature) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    Color(0xFFEC1380).copy(alpha = 0.1f),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            when (feature.icon) {
                is FeatureIcon.Vector -> Icon(
                    imageVector = feature.icon.icon,
                    contentDescription = feature.title,
                    tint = Color(0xFFEC1380),
                    modifier = Modifier.size(28.dp)
                )
                is FeatureIcon.PainterIcon -> androidx.compose.foundation.Image(
                    painter = feature.icon.painter,
                    contentDescription = feature.title,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = feature.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B0D14)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feature.description,
                fontSize = 12.sp,
                color = Color(0xFF665577)
            )
        }
    }
}
@Composable
@Preview
fun OnboardingFeaturesScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    OnboardingFeaturesScreen(navController)
}