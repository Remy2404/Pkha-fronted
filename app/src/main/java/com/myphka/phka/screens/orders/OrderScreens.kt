package com.myphka.phka.screens.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.myphka.phka.models.Order
import com.myphka.phka.models.OrderStatus
import com.myphka.phka.viewmodels.orders.OrderViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryScreen(
    viewModel: OrderViewModel = hiltViewModel(),
    onOrderClick: (String) -> Unit,
    onBackClick: () -> Unit,
    navController: NavHostController
) {
    val orders by viewModel.orderHistory.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Orders") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (orders.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No orders found")
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(orders) { order ->
                    OrderItemCard(order = order, onClick = { onOrderClick(order.id) })
                }
            }
        }
    }
}

@Composable
fun OrderItemCard(order: Order, onClick: () -> Unit) {
    Card(onClick = onClick) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("Order #${order.id}", style = MaterialTheme.typography.titleMedium)
                Text(SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(order.date), style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("${order.items.size} items", style = MaterialTheme.typography.bodyMedium)
                Text("$${order.total}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.height(8.dp))
            OrderStatusChip(status = order.status)
        }
    }
}

@Composable
fun OrderStatusChip(status: OrderStatus) {
    val color = when (status) {
        OrderStatus.PROCESSING -> Color(0xFFFFA500) // Orange
        OrderStatus.SHIPPED -> Color(0xFF1E90FF) // Blue
        OrderStatus.DELIVERED -> Color(0xFF32CD32) // Green
        OrderStatus.CANCELLED -> Color.Red
    }
    Surface(
        color = color.copy(alpha = 0.2f),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = status.name,
            color = color,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(
    orderId: String,
    viewModel: OrderViewModel = hiltViewModel(),
    onTrackOrderClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(orderId) {
        viewModel.loadOrderDetails(orderId)
    }
    val order by viewModel.currentOrder.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        order?.let {
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text("Order #${it.id}", style = MaterialTheme.typography.headlineSmall)
                Text("Placed on ${SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(it.date)}")
                Spacer(modifier = Modifier.height(16.dp))
                
                OrderStatusChip(status = it.status)
                Spacer(modifier = Modifier.height(16.dp))

                if (it.status != OrderStatus.DELIVERED && it.status != OrderStatus.CANCELLED) {
                    Button(
                        onClick = { onTrackOrderClick(it.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Track Order")
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                Text("Items", style = MaterialTheme.typography.titleMedium)
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(it.items) { item ->
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                            Text("${item.quantity}x", modifier = Modifier.width(32.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(item.productName, style = MaterialTheme.typography.bodyMedium)
                                Text(item.variants, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Text("$${item.price * item.quantity}")
                        }
                    }
                }
                
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total", style = MaterialTheme.typography.titleLarge)
                    Text("$${it.total}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderTrackingScreen(
    orderId: String,
    viewModel: OrderViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val orderState = viewModel.trackOrder(orderId).collectAsState(initial = null)
    val order = orderState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Track Order") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        order?.let {
            Column(modifier = Modifier.padding(padding).padding(32.dp)) {
                Text("Order Status", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(32.dp))

                TimelineNode(
                    title = "Order Placed",
                    isCompleted = true,
                    isLast = false
                )
                TimelineNode(
                    title = "Processing",
                    isCompleted = it.status != OrderStatus.CANCELLED,
                    isLast = false
                )
                TimelineNode(
                    title = "Shipped",
                    isCompleted = it.status == OrderStatus.SHIPPED || it.status == OrderStatus.DELIVERED,
                    isLast = false
                )
                TimelineNode(
                    title = "Delivered",
                    isCompleted = it.status == OrderStatus.DELIVERED,
                    isLast = true
                )
            }
        }
    }
}

@Composable
fun TimelineNode(title: String, isCompleted: Boolean, isLast: Boolean) {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (isCompleted) MaterialTheme.colorScheme.primary else Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(if (isCompleted) MaterialTheme.colorScheme.primary else Color.LightGray)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            title,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isCompleted) MaterialTheme.colorScheme.onSurface else Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )
    }
}

@Preview
@Composable
fun OrderHistoryScreen(){
    OrderHistoryScreen(onOrderClick = {}, onBackClick = {}, navController = rememberNavController())
}
