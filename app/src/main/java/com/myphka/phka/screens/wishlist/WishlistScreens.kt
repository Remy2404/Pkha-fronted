package com.myphka.phka.screens.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.myphka.phka.models.Product
import com.myphka.phka.viewmodels.wishlist.WishlistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    viewModel: WishlistViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val wishlistItems by viewModel.wishlist.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wishlist") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (wishlistItems.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Your wishlist is empty")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(padding)
            ) {
                items(wishlistItems) { product ->
                    WishlistItem(
                        product = product,
                        onClick = { onProductClick(product.id) },
                        onRemove = { viewModel.removeFromWishlist(product.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun WishlistItem(
    product: Product,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Card(onClick = onClick) {
        Column {
            Box(modifier = Modifier.height(150.dp).fillMaxWidth()) {
                AsyncImage(
                    model = product.imageUrl ?: product.imageRes,
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(id = product.imageRes),
                    error = painterResource(id = product.imageRes),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove", tint = MaterialTheme.colorScheme.error)
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(product.name, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
                Text("$${product.price}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareProductsScreen(
    products: List<Product>, // Passed from navigation or ViewModel
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compare Products") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Row(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            products.forEach { product ->
                Column(modifier = Modifier.weight(1f)) {
                    AsyncImage(
                        model = product.imageUrl ?: product.imageRes,
                        contentDescription = product.name,
                        modifier = Modifier.height(120.dp).fillMaxWidth(),
                        placeholder = painterResource(id = product.imageRes),
                        error = painterResource(id = product.imageRes),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(product.name, style = MaterialTheme.typography.titleSmall)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Price", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("$${product.price}", style = MaterialTheme.typography.bodyMedium)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Category", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(product.category, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
