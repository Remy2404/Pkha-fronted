package com.myphka.phka.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

data class BundleItem(
    val name: String,
    val size: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductBundlesScreen(
    navController: NavController
) {
    val bundleItems = listOf(
        BundleItem("Hydrating Face Serum", "30ml", "https://lh3.googleusercontent.com/aida-public/AB6AXuB9Kr48zBBz4Xlb-L3c1m8rPxxUWiuq6YfpnaDakzS2Bebzr3XhGkL6kU4kLSbGkRt8Rcs_2fGI17bFruX4FdwzSZDOpFA28seXQz1spHS5b6oKtF9Dq785g51BxZuAmYku_cyWr-no2WhghwLDDwU4elGamFY7VIt2ZYda-W-zLkNr9OE0ja_3arUetYR7V7GG9xd99rYazfr8dBZyv4xU9OC7bPNqfjQgQ-r00ULYZ6GuGaAxVMdM21iL0V2ZLhoRTn5cb6CjT-g"),
        BundleItem("Sunscreen SPF 50", "50ml", "https://lh3.googleusercontent.com/aida-public/AB6AXuAXCI6K9R2COO78xtjuZO8PPcbgcR52FqHuTZ02Gg6hgVJJb5jz2RDHLpJRh52MUg07MLRmeH3SpRX1Yljs1tF-_GFmZnD-atsM7MmwFKRLluwIeP1-ZojzF0YAKvp2lzXB0mIZSWKVpJWmXnCgKgVCV6eWB1_2qjMwHDTY35vHYFKJO7YLBV6SaCd4xBgWi9baq30F017J-7jcpCQiDQYTN9sD_978koKSH7bqwNxnSdsYEucixpPSMISK-u_z5sc3S139PT3sFik"),
        BundleItem("Lip Balm", "15ml", "https://lh3.googleusercontent.com/aida-public/AB6AXuC8rPAm6BCusFc4XOy-SH3QsgnVZqZcBZyLL0_Pt_IBrD-8HyYhjbx-aPYIaslvjYUUaQ06e9_0RiV4tr4Ge2MO3DJXUN_gbD_0QnCwVz2a6_jpU-VNf-WD5k6znsPCoSwfEGvl0-tA8_TiJqmNWS1FkbhclTjDfwVcnbVKJdmJ9eNqvZyukVBVRRVxyPSvRPsBznOyPoQvxzBQLu_0UrgyIhe-8aOIam1XBzZCQpOQ9argl5hCWhhlzberAj988L45Mc6UXqFSNSc")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Bundle Deals", fontWeight = FontWeight.Bold) },
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
                    onClick = { /* Add Bundle to Cart */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Add Bundle to Cart", fontWeight = FontWeight.Bold)
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
            // Bundle Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDnyyOSitJShjEc59Nbzc66Mu5DM-8D0SotEvD5aciqnip3Rm3UHdMLF9HxbNviEazhk6P9caIB9Ygl94TcRWwN39GE9-_a2CAp9jlrkiXOLYOHUgorsAZhVH32ZmDm833kfOaylCUFfV7aNPowKmBxRS0TnwJSDIuHS_cOGkd8W3gOgbeuigUt65cTySNb1RaCmH-Dt-UUhFvachoPa0c9kgoBF0UPX4vJoI0fRJxW4iuV9DENJhyq8FKWKUC8IVFeuALCkvJ2bbw",
                        contentDescription = "Bundle Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Summer Glow Bundle",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "3 items",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Button(
                                onClick = { /* Add Bundle */ },
                                shape = RoundedCornerShape(20.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                Text("Add Bundle")
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Get ready for summer with this bundle of essential skincare products.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Items in Bundle
            Text(
                text = "Items in this bundle",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                bundleItems.forEach { item ->
                    BundleItemRow(item)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Savings
            Text(
                text = "Savings",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Savings",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "$15.00",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun BundleItemRow(item: BundleItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = item.size,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
