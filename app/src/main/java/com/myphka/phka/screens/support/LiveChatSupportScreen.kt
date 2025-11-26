package com.myphka.phka.screens.support

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

data class ChatMessage(
    val id: String,
    val text: String,
    val isFromUser: Boolean,
    val timestamp: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveChatSupportScreen(navController: NavController) {
    var messageText by remember { mutableStateOf("") }
    
    val messages = listOf(
        ChatMessage("1", "Hi there! How can I help you today?", false, "10:30 AM"),
        ChatMessage("2", "Hi, I have a question about my order.", true, "10:31 AM")
    )
    
    val quickReplies = listOf(
        "Where is my order?",
        "How do I return an item?",
        "Shipping options?"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Support", fontWeight = FontWeight.Bold)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF4CAF50))
                            )
                            Text(
                                "Online Now",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Type a message...") },
                    trailingIcon = {
                        Row {
                            IconButton(onClick = { /* Attach photo */ }) {
                                Icon(Icons.Default.AddPhotoAlternate, contentDescription = "Attach")
                            }
                            IconButton(onClick = { /* Send message */ }) {
                                Icon(
                                    Icons.AutoMirrored.Filled.Send,
                                    contentDescription = "Send",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    },
                    shape = RoundedCornerShape(24.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(messages) { message ->
                    ChatMessageBubble(message)
                }
            }
            
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Quick Replies",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    quickReplies.take(2).forEach { reply ->
                        SuggestionChip(
                            onClick = { messageText = reply },
                            label = { Text(reply, fontSize = 12.sp) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChatMessageBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isFromUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isFromUser) {
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuC-IyA_0d8PD9w_iQPhQQxRLsMEB72WvCQRmOwZnyejOEcVdzDJUYXNs8ieZY9yaPDQX1cnmuKBO2B9fkQZJSvXWMBDZrFVTSMwjbDvszTA7PE1g3QuwFUZyccNeXKS2oUhzdz2vgpM5pwU-dWAXVo-gBZQ80MWZCqH3DrXvB4R2Gh0CsRdMQ4A1qSyfHAkzVNqgpzFqw4gdWJce_bcoNdldz1f1vMJ7VQC018Q9RfZ_dGpI7w2MPjuWNcdqcr8m7X3kZhbMRBvams",
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        
        Surface(
            color = if (message.isFromUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isFromUser) 16.dp else 4.dp,
                bottomEnd = if (message.isFromUser) 4.dp else 16.dp
            ),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = if (message.isFromUser) Color.White else MaterialTheme.colorScheme.onSurface
            )
        }
        
        if (message.isFromUser) {
            Spacer(modifier = Modifier.width(8.dp))
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuADK8AyLsy34MYAtvyVIyUGFeN_YDCAuG7Vq36bGCkQJIXaPRIAjiz9nXHEgUL_YfnPq0zk0GuVzHc1FjLik39f6zpPJXEmAdkO_UZ6eoX7l51X4k-5Wxa2oyt1ttPI0nL7RmOGbMgdQ6_7F4VNa8yaXS9dM0D9aNBhtKoyki6D5m0_jqjtY6H8MYcFKR8VW_kYgn2UVXCqF375Bll6IAa4XhQKrUe0dDMubFFe8e0BPoxVfsZ4mwmLuRSAGlEy44YM_qROn04hZJY",
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LiveChatSupportScreenPreview() {
    MaterialTheme {
        LiveChatSupportScreen(navController = rememberNavController())
    }
}
