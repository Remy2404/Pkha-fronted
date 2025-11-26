package com.myphka.phka.screens.legal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

data class ReturnStep(
    val title: String,
    val date: String,
    val isCompleted: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnsRefundsScreen(navController: NavController) {
    var selectedReason by remember { mutableIntStateOf(0) }
    var selectedMethod by remember { mutableIntStateOf(0) }
    
    val reasons = listOf("Wrong Size", "Damaged", "Defective", "Not as Described", "Other")
    val methods = listOf("Drop-off", "Pickup")
    
    val returnSteps = listOf(
        ReturnStep("Return Requested", "May 20, 2024"),
        ReturnStep("Return in Transit", "May 22, 2024"),
        ReturnStep("Refund Processed", "May 25, 2024")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Returns & Refunds", fontWeight = FontWeight.Bold) },
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
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 3.dp
            ) {
                Button(
                    onClick = { /* Submit return */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Submit Return", modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Column {
                    Text(
                        "Select Order",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Card {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAcI7Io0-588AWnXs5HkjJAttHX3mOqM9v9FgX2YDbJUh1RPlq7v5RbpyUyIbxQYbeHaOXKJ3Wrui4QjNzyNDU91dRXePdkLDlSknR-HSQzaQdRHoSJniYQGleE_SMd48fvA6I_GiXdeUduP3Gw4FMpcMazK9GeNbOsH7JTmWti87muLcLML-DcrDkRcBXhT8Dvb6SmI7uLimUc_HOu00GbzzSteFevry-kZgkG-0RQ73Tr3N0Pzj-pZ3NTQcgAnolbNP7fcpjJu6M",
                                contentDescription = null,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text("Delivered on May 15, 2024", fontWeight = FontWeight.Medium)
                                Text(
                                    "Order #1234567890",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
            
            item {
                Column {
                    Text(
                        "Reason for Return",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    reasons.forEachIndexed { index, reason ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .selectable(
                                    selected = selectedReason == index,
                                    onClick = { selectedReason = index }
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedReason == index) 
                                    MaterialTheme.colorScheme.primaryContainer 
                                else MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(reason, fontWeight = FontWeight.Medium)
                                RadioButton(
                                    selected = selectedReason == index,
                                    onClick = { selectedReason = index }
                                )
                            }
                        }
                    }
                }
            }
            
            item {
                Column {
                    Text(
                        "Return Method",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    methods.forEachIndexed { index, method ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .selectable(
                                    selected = selectedMethod == index,
                                    onClick = { selectedMethod = index }
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedMethod == index) 
                                    MaterialTheme.colorScheme.primaryContainer 
                                else MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(method, fontWeight = FontWeight.Medium)
                                RadioButton(
                                    selected = selectedMethod == index,
                                    onClick = { selectedMethod = index }
                                )
                            }
                        }
                    }
                }
            }
            
            item {
                Column {
                    Text(
                        "Return Tracking",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    returnSteps.forEach { step ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Surface(
                                modifier = Modifier.size(28.dp),
                                shape = RoundedCornerShape(14.dp),
                                color = MaterialTheme.colorScheme.primary
                            ) {}
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(step.title, fontWeight = FontWeight.Medium)
                                Text(
                                    step.date,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReturnsRefundsScreenPreview() {
    MaterialTheme {
        ReturnsRefundsScreen(navController = rememberNavController())
    }
}
