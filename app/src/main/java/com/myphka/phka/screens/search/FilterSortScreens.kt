package com.myphka.phka.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myphka.phka.models.FilterOptions
import com.myphka.phka.models.SortOption
import com.myphka.phka.viewmodels.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onApply: () -> Unit,
    onClose: () -> Unit
) {
    val currentFilters by viewModel.activeFilters.collectAsState()
    
    // Local state for editing filters before applying
    var priceRange by remember { mutableStateOf(currentFilters.priceRange ?: 0f..1000f) }
    var selectedBrands by remember { mutableStateOf(currentFilters.brands.toSet()) }
    var selectedRating by remember { mutableStateOf(currentFilters.rating) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Filters") },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                },
                actions = {
                    TextButton(onClick = {
                        // Reset logic
                        priceRange = 0f..1000f
                        selectedBrands = emptySet()
                        selectedRating = null
                    }) {
                        Text("Reset")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    viewModel.updateFilters(
                        FilterOptions(
                            priceRange = priceRange,
                            brands = selectedBrands.toList(),
                            rating = selectedRating
                        )
                    )
                    onApply()
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Apply Filters")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Price Range", style = MaterialTheme.typography.titleMedium)
            RangeSlider(
                value = priceRange,
                onValueChange = { priceRange = it },
                valueRange = 0f..1000f,
                steps = 19
            )
            Text("$${priceRange.start.toInt()} - $${priceRange.endInclusive.toInt()}")

            Spacer(modifier = Modifier.height(24.dp))

            Text("Brand", style = MaterialTheme.typography.titleMedium)
            val brands = listOf("Phka", "L'Oreal", "Maybelline", "MAC") // Dummy brands
            brands.forEach { brand ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = selectedBrands.contains(brand),
                        onCheckedChange = { checked ->
                            selectedBrands = if (checked) selectedBrands + brand else selectedBrands - brand
                        }
                    )
                    Text(brand)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Rating", style = MaterialTheme.typography.titleMedium)
            val ratings = listOf(4, 3, 2, 1)
            ratings.forEach { rating ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedRating == rating,
                        onClick = { selectedRating = rating }
                    )
                    Text("$rating Stars & Up")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onApply: () -> Unit,
    onClose: () -> Unit
) {
    val currentSort by viewModel.activeSort.collectAsState()
    var selectedSort by remember { mutableStateOf(currentSort) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sort By") },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    viewModel.updateSort(selectedSort)
                    onApply()
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Apply")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            SortOption.values().forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (selectedSort == option),
                            onClick = { selectedSort = option }
                        )
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedSort == option),
                        onClick = null // Handled by Row selectable
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(option.displayName)
                }
            }
        }
    }
}
