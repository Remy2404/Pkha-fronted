package com.myphka.phka.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.myphka.phka.models.Product
import com.myphka.phka.models.ProductVariant
import com.myphka.phka.models.VariantType
import androidx.compose.ui.tooling.preview.Preview
import com.myphka.phka.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductVariantsSheet(
    product: Product,
    onAddToCart: (List<ProductVariant>, Int) -> Unit,
    onDismiss: () -> Unit
) {
    // Dummy variants for now, ideally passed in or fetched
    val sizes = listOf(
        ProductVariant("s1", "Small", VariantType.SIZE),
        ProductVariant("s2", "Medium", VariantType.SIZE),
        ProductVariant("s3", "Large", VariantType.SIZE)
    )
    val colors = listOf(
        ProductVariant("c1", "Yellow", VariantType.COLOR), // Hex codes would be better
        ProductVariant("c2", "Blue", VariantType.COLOR),
        ProductVariant("c3", "Red", VariantType.COLOR)
    )

    var selectedSize by remember { mutableStateOf<ProductVariant?>(null) }
    var selectedColor by remember { mutableStateOf<ProductVariant?>(null) }
    var quantity by remember { mutableStateOf(1) }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Select Options", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            // Product Info Summary
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceVariant))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(product.name, style = MaterialTheme.typography.titleMedium)
                    Text("$${product.price}", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Size Selection
            Text("Size", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                sizes.forEach { size ->
                    FilterChip(
                        selected = selectedSize == size,
                        onClick = { selectedSize = size },
                        label = { Text(size.name) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Color Selection
            Text("Color", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                colors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(getColorFromName(color.name))
                            .border(
                                width = if (selectedColor == color) 2.dp else 0.dp,
                                color = if (selectedColor == color) MaterialTheme.colorScheme.primary else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable { selectedColor = color }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Quantity
            Text("Quantity", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { if (quantity > 1) quantity-- }) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease")
                }
                Text("$quantity", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 16.dp))
                IconButton(onClick = { quantity++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Increase")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val variants = listOfNotNull(selectedSize, selectedColor)
                    onAddToCart(variants, quantity)
                },
                enabled = selectedSize != null && selectedColor != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add to Cart")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun getColorFromName(name: String): Color {
    return when (name.lowercase()) {
        "yellow" -> Color.Yellow
        "blue" -> Color.Blue
        "red" -> Color.Red
        "green" -> Color.Green
        else -> Color.Gray
    }
}

@Preview
@Composable
fun ProductVariantsSheetPreview() {
    ProductVariantsSheet(
        product = Product("1", "Test Product", 99.99, R.drawable.product_001),
        onAddToCart = { _, _ -> },
        onDismiss = {}
    )
}
