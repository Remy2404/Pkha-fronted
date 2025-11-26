package com.myphka.phka.screens.loyalty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
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

data class Reward(
    val id: String,
    val title: String,
    val description: String,
    val points: Int,
    val imageUrl: String
)

data class EarnMethod(
    val title: String,
    val description: String,
    val icon: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoyaltyProgramScreen(navController: NavController) {
    val userPoints = 1250
    val userName = "Sophia Chen"
    
    val rewards = listOf(
        Reward(
            "1",
            "Free Shipping",
            "Enjoy complimentary shipping on your next order.",
            1000,
            "https://lh3.googleusercontent.com/aida-public/AB6AXuByN0D-POPB9wlN9Bpb1hzWSKUVXOtWrb1DfaWs9_auKA36eBipaTkYf0P6UAE85cNGP2Otlnraa3-I07UiXOcl7Niy6gAD7t6kSQpMA1kK5lhpo3nrEen1l93R9DtqWHwEsWsSgZSV0LAguphKMnVIdtmEvrrSR_03h-ddemP68taKV7HnpkAoH6pcPeBeKP7SQUvYCyBW2Klj0VLzVMWQBb0PGJ5bxNfyY-htFPs9BQnsgFAK15pxBz7sPE9X-DulXX-X_7qp6hc"
        ),
        Reward(
            "2",
            "10% Off",
            "Get a discount on your favorite products.",
            500,
            "https://lh3.googleusercontent.com/aida-public/AB6AXuDO9AGJX_et_I2mtdIfbgvEQTuItMgU7WAwjn671ubZxTwb42XpiZUJ8fDSKNHmF0tU_XufcrcpWAV-lSxWjXTbMBfIkmmk2jfqDIZGOXaENTcs6Ja1rKE3bieVD9kMj99Ko30cLXriaLv1NQsONO_WvYuz6pUbmc6DAAR8jGjIUNpqYrtukRWkInXdP2ps2n6sPxly3MSbWQUS7n3lHIBRUKNNq5O92K1f9hW5slGs2K7sXwVV2JrZ5UCOsKWeshGr8pTZJK9dYGY"
        )
    )
    
    val earnMethods = listOf(
        EarnMethod(
            "Shop",
            "Earn 1 point for every $1 spent",
            { Icon(Icons.Default.ShoppingBag, contentDescription = null) }
        ),
        EarnMethod(
            "Leave Reviews",
            "Earn 50 points for each review",
            { Icon(Icons.Default.Star, contentDescription = null) }
        ),
        EarnMethod(
            "Refer a Friend",
            "Earn 100 points for referring a friend",
            { Icon(Icons.Default.People, contentDescription = null) }
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Beauty Rewards", fontWeight = FontWeight.Bold) },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box {
                        AsyncImage(
                            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAcxDUy_4ev584jg8MU7u4FSKsviCJUPToDzfsSOBo6_q94tugDWRm_tG-q8-8i8OJU2HNI4w0F4vWfTaIwUOBAbvu6GjFygeZ_r36FU23HrqoXQnqoxs30nkc-U0iXUZ9qoSoODyZb0chE-4ByZnelRJIATTGUL2Ll0BbeobG8PNdIA7lW9kZfJx6R04PJ6FJrLax5Oa-T-_nHHbpYmp15hk5rCp2VaPr4BLQVd1xEWpsO1DtEXGQkwfR4_FKRbMwTbJLzeroHAjo",
                            contentDescription = null,
                            modifier = Modifier
                                .size(128.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Surface(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(32.dp),
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(userName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(
                        "$userPoints points",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            
            item {
                Text(
                    "Redeem",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(rewards) { reward ->
                RewardCard(reward = reward)
            }
            
            item {
                Text(
                    "Earn More Points",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(earnMethods) { method ->
                EarnMethodCard(method = method)
            }
        }
    }
}

@Composable
fun RewardCard(reward: Reward) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = reward.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "${reward.points} points",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    reward.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    reward.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* Redeem */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Redeem")
                }
            }
        }
    }
}

@Composable
fun EarnMethodCard(method: EarnMethod) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    method.icon()
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(method.title, fontWeight = FontWeight.SemiBold)
                Text(
                    method.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoyaltyProgramScreenPreview() {
    MaterialTheme {
        LoyaltyProgramScreen(navController = rememberNavController())
    }
}
