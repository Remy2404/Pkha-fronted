package com.myphka.phka.screens.tips

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

data class CategoryItem(
    val name: String,
    val imageUrl: String
)

data class ArticleItem(
    val title: String,
    val author: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeautyTipsScreen(
    navController: NavController
) {
    var selectedCategory by remember { mutableStateOf("Skincare") }
    val categories = listOf(
        CategoryItem("Morning Skincare Routine", "https://lh3.googleusercontent.com/aida-public/AB6AXuBNzShwI1KzcJzI6GoXSSd_G3s_D2D3fCEgSXV0C9m4eGhA7OoB6wMiEdUeFosWpYvMReatsyrkaysnO1hI2UvB7SaSSa7pxsnxrOjf8A7x0nEcyCgk_MITI-aEKw9l39bDcQufNNApLQtDy2NkQsfYhvCSZyh9ubfWj2lHe58AmJK6aWOwDvcBB5v87wqpP8tcBkyJf8NkYS1NukpW2_nBBXleca0wuqjFxPi6FGIsZcdvOKzQC6FxKCUP-wnIh4t5sN4pAooNoyA"),
        CategoryItem("Night Skincare Routine", "https://lh3.googleusercontent.com/aida-public/AB6AXuDsHLuKvO18cye2_9-tLDwhp0iJYahO7BOpq1oYTRvZ1VPwas0-lua6rkyFGqlHuos66Q-48SzlOOGRc6gMbHXI441AIXyu0tSusSjiAmSf3jbeQ9JXB-DcoOnWlxTFH2F0b_Mfb-OzlTnuWD2QLdiF0u7yWi3Ot4mGfZ-ZwBoJOHPLlbCarmv2Wbvgg5U8_W8tGuzBxMhl13PUktB7AYXOVubRQjpCvdGR4h99pQfQiPYUM0QLCwPrWmEEGSfbsmpS7T7zY18EZPU"),
        CategoryItem("Acne Treatment", "https://lh3.googleusercontent.com/aida-public/AB6AXuDd5EDQ2nw9lqw2KvcFSCTY6HThvzav0S34UKTYHfMsruxv1d5FaXigIqeB4tNGxaQoHgbnusT-fI5jNIaEFEXwbY3-AAUjxarMQI3D-qKOzOT0lWw6rBz_Hl81LzkmAGx2iWNNvDnHQOBvPr1_9SC2NO4aBlR-TkrclGJ-m7gPukgpr-s_Rd76se1FzuRYICnoR6SOVjruUsCcFVRMnbLtSBxyYjn3lIFQM0BaY5eO0lhHR1aQ2XxBakrPdMhkun7hfRtU8-2UhHw"),
        CategoryItem("Anti-Aging Tips", "https://lh3.googleusercontent.com/aida-public/AB6AXuAZ3Kz7aS9sM-j8wRr1i6FJQzQQCBqeiYL2aRLy84jLNjMgqRYyKe1jqSO5_Mjt36y1uzxGb8LemloeA_iqE5pnC9YGPAzpunFYwRWByEW1nQsPQTUglzAQEQ-FEeW56_bd0qA-BhsRyjlh48wCEL3uBnf2zWt3A1R91eTDBZbanAhoFCo7jM12b9W-__JTEqZGENfOeNlerfm97jz1weG877EXv3Hii04rgpvLbbr1b3Bpl_381vXsvO2gl_Trc5qTW_gb0Zpr0vY")
    )

    val articles = listOf(
        ArticleItem("The Ultimate Guide to Choosing the Right Foundation", "By Dr. Anya Sharma", "https://lh3.googleusercontent.com/aida-public/AB6AXuA7YPQLrY3KRKU4O6LnNw0nMzLps3uYC5lvlsPXozsplVv59VsSwDLg0efLRxbCJHN2qSNnugSiAiewmRVtEWKcbzbHOzhr3IGGQDB377oJlpILRG2f5S7-dzDAbM8emjoykgZtREVle4VWtpaGluoimFUrc81oZ-DLI02eeVY5SZ56YZmN2gPGj5wpnum23LBM383Na3Iw6vVFsfvByg27T35J84r720hc9CWDr4u0HZYWrfFdwL2eRr72OXlaBLeInuh_fy4I1r0"),
        ArticleItem("How to Achieve a Flawless Cat-Eye Look", "By Isabella Rossi", "https://lh3.googleusercontent.com/aida-public/AB6AXuBRJl5Yu8QA5E0hhO2y0d4MLcMwztwpx7rypvT7ZWVGqJWYRRmyrMaBoZMxo-AR9qp1NGXjl3N1HKzoxlruhS60VmkS1ioEcNESPQsC2u8NLY2i9_QlpbcDnSm59rf15XUxoW8tnQl_tjJxHZnAZ2VOzOlw87Hbx73VHeRHms8rm1DpFIwRKwQtBq87eC6mnSJkXVDizktwWZjOgH9TJK94vpcrV2RTEbrm84VX-Bw-8FqPMfnS27KnJmPTv4HJobLc3w9sLlI2i8Q")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Beauty Tips", fontWeight = FontWeight.Bold) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Tip of the Day
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Tip of the Day",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column {
                        AsyncImage(
                            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuCYMvE0Q_6HRxu-0O0UR9tNtoBpc76gW1ETDUeYWhsMCAV9RPr5kq33K_UxtK1iUtKD8Rn1WCQ4Fdfr3cs_gJZwsC_U0DRotnfC7iA6RIQuPLvFFqF5IITm4PZ1yfy4Kz3mEVIiBCQy7zJ8U-CLy05xN5c3Pmd-t3Xnd9zNOqF_fU_8EMzfv_GFQygzNKIXlGqfNn5sfjMO3Z5gFNc9Xp0zkek-bXUfKW3ecw73bi3OYhviF6p2l_pc1Mwwi6z8ZqDpsBMdhEfx2MQ",
                            contentDescription = "Tip of the Day",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Hydrate your skin",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Drink plenty of water throughout the day to keep your skin hydrated and glowing.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Categories
            Column {
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                TabRow(
                    selectedTabIndex = 0, // Simplified for now
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    divider = { HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant) }
                ) {
                    Tab(
                        selected = true,
                        onClick = { selectedCategory = "Skincare" },
                        text = { Text("Skincare", fontWeight = FontWeight.Bold) }
                    )
                    Tab(
                        selected = false,
                        onClick = { selectedCategory = "Makeup" },
                        text = { Text("Makeup", fontWeight = FontWeight.Bold) }
                    )
                    Tab(
                        selected = false,
                        onClick = { selectedCategory = "Hair Care" },
                        text = { Text("Hair Care", fontWeight = FontWeight.Bold) }
                    )
                }
                
                // Category Grid
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        CategoryCard(categories[0], Modifier.weight(1f))
                        CategoryCard(categories[1], Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        CategoryCard(categories[2], Modifier.weight(1f))
                        CategoryCard(categories[3], Modifier.weight(1f))
                    }
                }
            }

            // Expert Articles
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Expert Articles",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    articles.forEach { article ->
                        ArticleItemRow(article)
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCard(category: CategoryItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            model = category.imageUrl,
            contentDescription = category.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ArticleItemRow(article: ArticleItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Article",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.author,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        AsyncImage(
            model = article.imageUrl,
            contentDescription = article.title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )
    }
}
