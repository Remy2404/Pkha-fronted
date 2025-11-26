package com.myphka.phka.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

data class RecommendedProduct(
    val name: String,
    val description: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizResultsScreen(
    navController: NavController
) {
    val recommendedProducts = listOf(
        RecommendedProduct("Oil-Control Cleanser", "Gentle yet effective", "https://lh3.googleusercontent.com/aida-public/AB6AXuBf9svQIhVph9as6z-GLdUXf1_h40IaQ7W9xTrKexYTCYk6hpEm28WhED7AdLE8OrLWLh7D7SUuwms5bkDwBJG3HYGwETviwbK5Go2ys2ZhMnGC4phV67sIPhE6GhhMtF2p0LVBE-0Cd4lyed8VHVK_JkS-xjszecc1iLpW8jDeX7OAnSLSjhm2kpo7vEk_yJyqcpBlrFG_y-wPjaotBg1FLKlNvRZApYsaXo8ID1ik1fP3Bt1E27ZCRsiANmUNauSJWRx3YYncCkA"),
        RecommendedProduct("Mattifying Moisturizer", "Lightweight & non-comedogenic", "https://lh3.googleusercontent.com/aida-public/AB6AXuArBYpD9BG9pJOkO_z3tnhbBKstwkZ7KJzrVeQgf0w6Pb8EFsW_RIKk9jK16I-WLRJ_5o3va6qIpHWQickAxtDrN1mUcONiLWskvc9pYXriHH-g5i-gNHhDLQhV3qFkepzE62nsuPMVjE3_Z92x9fykn8ugcBNjGVyR8kb-X8KV38e0DMo5IpAvHLQxOtsdjjBST5WcxBJOfc8FxkAsZQauvwjDRZUU4lJEpY9wQoZdDUwd0PiD0df0CN2-0eem8UFcJO-3q7K0-wU"),
        RecommendedProduct("Clay Mask", "Deep-cleansing for pores", "https://lh3.googleusercontent.com/aida-public/AB6AXuAOb0TMmueNtCDw5MXMgqPI7IRyezMvAku5efi18oacx0EFd0MfnnvhZOnmilVu6eMdkDJbIV-zsZkJ-61UfgJXY38s9Z6Pw1XBCjsCeyv1cJdW15q6_lsKJY5yodNocJ3EzLExE7QXUrV74DbcNVpG6tLMGu3VwnERRtivvKylQFUmH1inFsg2-9RyXFca1995wuaGlFlruPPE1MPgyXNLUUXbcUNlLRu148bV6L7MXDGTRwrGbVEfUpFTq7KOKaKT4G9oR5c5MNs"),
        RecommendedProduct("Exfoliating Toner", "Removes dead skin cells", "https://lh3.googleusercontent.com/aida-public/AB6AXuBc56Smk-NqsFqLapx7au7aF_uV1_MYnA-dg5n-L2ee4rUReOD_LjcCtGJDIYZH2-x8Ng6slIdrOCL-FJAfb4RMT4O56l1B8c8hldOcfEaxpKpQT8Q6fovZOkKLT2SKSRmP3grcreqQgQ0Gm7n1gGhxV3g9dWbXri_HvIBW0na__Zc_esGIgv7zbdjUOOOwgEM5fhDS1dGiwPtWUxPd4UKblUMLNQIc0pC6aORt5wcxK5l9W_bvuSFWyZo0vbjIKUFDnTuxasYsVBA")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Beauty Quiz", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
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
        ) {
            // Skin Profile
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Your Skin Profile",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.Top) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "OILY",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Your Skin Type",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Your skin tends to produce excess oil, leading to a shiny complexion and potential breakouts. Focus on oil-control and balancing products.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            AsyncImage(
                                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBc56Smk-NqsFqLapx7au7aF_uV1_MYnA-dg5n-L2ee4rUReOD_LjcCtGJDIYZH2-x8Ng6slIdrOCL-FJAfb4RMT4O56l1B8c8hldOcfEaxpKpQT8Q6fovZOkKLT2SKSRmP3grcreqQgQ0Gm7n1gGhxV3g9dWbXri_HvIBW0na__Zc_esGIgv7zbdjUOOOwgEM5fhDS1dGiwPtWUxPd4UKblUMLNQIc0pC6aORt5wcxK5l9W_bvuSFWyZo0vbjIKUFDnTuxasYsVBA",
                                contentDescription = "Skin Type Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            // Recommended Products
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Text(
                    text = "Recommended Products",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(recommendedProducts) { product ->
                        RecommendedProductItem(product)
                    }
                }
            }

            // Actions
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { /* Create Routine */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Create a Routine", fontWeight = FontWeight.Bold)
                }
                OutlinedButton(
                    onClick = { /* Save & Share */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                ) {
                    Text("Save & Share Results", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun RecommendedProductItem(product: RecommendedProduct) {
    Column(modifier = Modifier.width(160.dp)) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = product.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 2
        )
    }
}
