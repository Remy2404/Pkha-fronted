package com.myphka.phka.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

data class PriceAlertItem(
    val name: String,
    val targetPrice: String,
    val currentPrice: String,
    val change: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceAlertsScreen(
    navController: NavController
) {
    val alerts = listOf(
        PriceAlertItem("Radiant Glow Serum", "$25.00", "$28.00", "+12%", "https://lh3.googleusercontent.com/aida-public/AB6AXuDg4xtKdW0AQsXlZSlIwfm8UsBqjgXP5_5eANb8vZ0v4I5vjq6OaJjqbJ3jH3gwedb2djkxi8J9XntfrQWM4kInUjM5D1hl7L7rsgL0Gg78W3P-x86vJO2Lm7JCt6HDn9DkJfZKMcElWZcun1l9xWRfqv1SiLb3-G-fecnupFVsuUR8y2nJ_0wO2SxYLByfyxWGmSHOUEohbu-OitSz0jrGEH2v8eWiRI3tUnh3I5kyEh1-0ZliezovVq2ddXLaGVdKMaR-L3icZFE"),
        PriceAlertItem("Hydrating Face Mask", "$15.00", "$18.00", "+20%", "https://lh3.googleusercontent.com/aida-public/AB6AXuAwW1KSsQL_O4905Gzm_S89MuU1KJZInl7z58BEfAMi5SUeZfTczuidsUtcaXOCsLzN_x8ZMWPcl7E7mv_pTA4SBjatEVGBeQ6M-Ag-eCmHgoynK1DAGOF5DaMFq7Hv4FO_mKp7MM61AzRfG04QAJbJjjERKUcnJ5T8CiO3XSb2shKkIDf5wf_5xvzTkiV60_BOQlp8Cvo_VvNwnlKhePlT2Z7k2vohWFfkSTH_rbjbHKPGTQSY8neOFG0wx4RZRwRtHIv9J5mddEc"),
        PriceAlertItem("Anti-Aging Cream", "$40.00", "$45.00", "+12.5%", "https://lh3.googleusercontent.com/aida-public/AB6AXuBcaYNd-apXWZkm5YaXVUpR9cVLBtxmmxjC-nc1iW3qq2dn3gofmbqEUMr41MkPldRG0aDFpBF2Z1JBzwda5vnbM-SRZ3vDoX3WTbBcvcYRFEJORWOz1emt2iRpjnML2HJ-cjG-t64txOrXoCdkHh0YuuC-bPym3KOzVaMkOjSc2fkSsWiHjkeeoBquwP088p-h1wzY3Xzu4spx8xtzwPb0HL0a3QOAKReG7f3VCmh5RsFb26mT2QBycWHdY1KdkVrKPFD2wBnkbfA")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Price Alerts", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background,
                tonalElevation = 8.dp
            ) {
                Button(
                    onClick = { /* Create New Alert */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Default.AddAlert, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Create New Alert", fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text(
                text = "Active Alerts",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                alerts.forEach { alert ->
                    PriceAlertRow(alert)
                }
            }
        }
    }
}

@Composable
fun PriceAlertRow(alert: PriceAlertItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = alert.imageUrl,
            contentDescription = alert.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = alert.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Target: ${alert.targetPrice}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = alert.currentPrice,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = alert.change,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF22C55E), // Green color
                fontWeight = FontWeight.Medium
            )
        }
    }
}
