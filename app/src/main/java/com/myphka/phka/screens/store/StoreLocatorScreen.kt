package com.myphka.phka.screens.store

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
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

data class StoreItem(
    val name: String,
    val distance: String,
    val status: String,
    val statusColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreLocatorScreen(
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }
    val stores = listOf(
        StoreItem("Phka - Union Square", "1.2 miles away", "Open until 8 PM", Color(0xFF16A34A)),
        StoreItem("Phka - Mission District", "2.5 miles away", "Open until 9 PM", Color(0xFF16A34A)),
        StoreItem("Phka - Richmond District", "3.8 miles away", "Open until 7 PM", Color(0xFFDC2626)),
        StoreItem("Phka - SOMA", "4.1 miles away", "Closed", Color.Gray)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Store Locator", fontWeight = FontWeight.Bold) },
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
                .padding(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search for a location") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { /* Use My Location */ }) {
                        Icon(Icons.Default.MyLocation, contentDescription = "My Location", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Map Placeholder
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAXMA8BKM43MuPqOyIVOdlrVcNizfSe9CgZof1R36rW13yBweejkYeMC0UuIc-BeusyYqj5k3_k5da5h2b8UCyOA8wZT-gC3KogLzUsWgwFQ6AEGjJQLREMpHkc9mFoBpP18xhMiUyCmrY4c2MyROdurzY2f-8Umbiw8XQje0eWwH6eCV-TQkyRYLVGAZuS3I2ZowQvBy1ccgp5b47gA1wBLEDHSrqQ8ljnANIAzyzLNCgOyNi7fAuC1XxDTbbqd5QrSUEAOuT6rcU",
                contentDescription = "Map",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nearby Stores
            Text(
                text = "Nearby Stores",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                stores.forEach { store ->
                    StoreItemRow(store)
                }
            }
        }
    }
}

@Composable
fun StoreItemRow(store: StoreItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = store.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = store.distance,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = store.status,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = store.statusColor
        )
    }
}
