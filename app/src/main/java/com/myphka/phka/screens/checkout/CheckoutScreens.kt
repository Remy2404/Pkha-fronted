package com.myphka.phka.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myphka.phka.models.Address
import com.myphka.phka.models.PaymentMethod
import com.myphka.phka.viewmodels.checkout.CheckoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutAddressScreen(
    viewModel: CheckoutViewModel = hiltViewModel(),
    onNextClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val addresses by viewModel.savedAddresses.collectAsState()
    val selectedAddress by viewModel.selectedAddress.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shipping Address") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = onNextClick,
                enabled = selectedAddress != null,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Next")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(addresses) { address ->
                AddressItem(
                    address = address,
                    isSelected = address == selectedAddress,
                    onClick = { viewModel.selectAddress(address) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                OutlinedButton(
                    onClick = { /* Navigate to Add Address Form */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Add New Address")
                }
            }
        }
    }
}

@Composable
fun AddressItem(address: Address, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        ),
        border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text(address.name, style = MaterialTheme.typography.titleMedium)
            Text(address.recipientName, style = MaterialTheme.typography.bodyMedium)
            Text("${address.street}, ${address.city}", style = MaterialTheme.typography.bodyMedium)
            Text(address.phoneNumber, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutPaymentScreen(
    viewModel: CheckoutViewModel = hiltViewModel(),
    onNextClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val selectedMethod by viewModel.selectedPaymentMethod.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Payment Method") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = onNextClick,
                enabled = selectedMethod != null,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Next")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            PaymentOption(
                icon = Icons.Default.CreditCard,
                title = "Credit Card",
                isSelected = selectedMethod is PaymentMethod.CreditCard,
                onClick = { viewModel.selectPaymentMethod(PaymentMethod.CreditCard("1234", "User", "12/25", "Visa")) } // Dummy data
            )
            PaymentOption(
                icon = Icons.Default.Money,
                title = "Cash on Delivery",
                isSelected = selectedMethod is PaymentMethod.COD,
                onClick = { viewModel.selectPaymentMethod(PaymentMethod.COD) }
            )
            PaymentOption(
                icon = Icons.Default.LocalShipping, // PayPal icon placeholder
                title = "PayPal",
                isSelected = selectedMethod is PaymentMethod.PayPal,
                onClick = { viewModel.selectPaymentMethod(PaymentMethod.PayPal) }
            )
        }
    }
}

@Composable
fun PaymentOption(icon: ImageVector, title: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutReviewScreen(
    viewModel: CheckoutViewModel = hiltViewModel(),
    onOrderPlaced: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val subtotal by viewModel.subtotal.collectAsState()
    val address by viewModel.selectedAddress.collectAsState()
    val payment by viewModel.selectedPaymentMethod.collectAsState()
    val orderResult by viewModel.orderPlacementResult.collectAsState()

    LaunchedEffect(orderResult) {
        orderResult?.onSuccess { orderId ->
            onOrderPlaced(orderId)
            viewModel.resetOrderState()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Review Order") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { viewModel.placeOrder() },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Place Order - $${subtotal + 5.0 + (subtotal * 0.1)}") // Approx total
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            item {
                Text("Shipping Address", style = MaterialTheme.typography.titleMedium)
                Text("${address?.street}, ${address?.city}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                
                Text("Payment Method", style = MaterialTheme.typography.titleMedium)
                Text(payment?.name ?: "", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                
                Text("Items", style = MaterialTheme.typography.titleMedium)
            }
            items(cartItems) { item ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text("${item.quantity}x ${item.product.name}", modifier = Modifier.weight(1f))
                    Text("$${item.totalPrice}")
                }
            }
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Subtotal")
                    Text("$${subtotal}")
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Shipping")
                    Text("$5.00")
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Tax")
                    Text("$${subtotal * 0.1}")
                }
            }
        }
    }
}

@Composable
fun OrderConfirmationScreen(
    orderId: String,
    onContinueShopping: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.CheckCircle,
            contentDescription = "Success",
            tint = Color.Green,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text("Order Placed!", style = MaterialTheme.typography.headlineMedium)
        Text("Order ID: $orderId", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onContinueShopping, modifier = Modifier.fillMaxWidth()) {
            Text("Continue Shopping")
        }
    }
}
