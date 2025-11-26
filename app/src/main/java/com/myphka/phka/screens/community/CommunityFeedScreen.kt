package com.myphka.phka.screens.community

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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

data class CommunityPost(
    val id: String,
    val userName: String,
    val userAvatar: String,
    val timeAgo: String,
    val content: String,
    val imageUrl: String?,
    val likes: Int,
    val comments: Int,
    val isLiked: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityFeedScreen(navController: NavController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("For You", "Following")
    
    val posts = listOf(
        CommunityPost(
            "1",
            "Sophia Carter",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuAX5lEcyuFKoRzeq8lXIf3fQ8obPonS0TkosLm1v14QEKQU1z9ax7zDaNZXuXwd3365HrxZu1ouiH7k7dDjP-MdME8j4CYyoJFPBSa2VPx1Ct57f-O3pJlMh0Ajk9LPmjRRCSTyaaj1xQN21jPEyXFIwm_ldoEub7VxC-4XPE0fc5oW-J0yIzbmaaWR8OPTHineQaVlPeQY4JVBKmruvsj3azt-Niks5NID-evmEarNdwAS4J1qw9qMAY1Nvya7YrW4OqYeLb02f34",
            "2d ago",
            "Get ready with me for a night out! This look is perfect for any occasion. #makeup #nightout #beauty",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuDuLh3L1kHgS36Kx-50JOMZYiDvWBfMfI8yX8INR2cm2P77-Xw_Ja6aOEnem1AcfpH_syqQ_byuXILlBc0q8QCjJFbxS9FOd-oLNNL7t3Z1p0SACPkeS73-3K1RPltHR93ZC5Z1aAl5qsaeYT9Rx3F4TYx7I3Z9vNmSBNOfFLwoJ0WqwRCRTeRuDMq6MjeGsZWWQwNP6BsCgfveo95a4cKL6gxtWur5fC9tkpRxHKypTpBKiAClxwDY3bx2LBIw8zWgUZGCcyY4z9U",
            234,
            12,
            true
        ),
        CommunityPost(
            "2",
            "Olivia Bennett",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuCACfbPZt5G01EDkgeEIjOVNmFPxfEUpcU_1QYiA5lC8ixEJHF2z-icyBs9SQB9MHbVN-GoPfmXvUMwasyMJ2OjHfsQevH9xTPeXojteZhoOxn_-NoN9h51Q8Z-JAvXxOCn4DX6FQV1FNBsiDGxEM3jAbR3yg-NiKpsdIl16AxL6qkAirxWDXwkVeI4XeRvGqwWtdXK-V7-ox_wAz5fkeYq2dt5qxxFqx7pB_cySO67K-uyf199fJkllh3cEDvyRN35fq_iprf5ivA",
            "1d ago",
            "My skincare routine for achieving that perfect glow! #skincare #glowingskin #beauty",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuAttt3tHrjzsjUJYDwbZF-rAAHnKopnSa8yLxzBAL4_SR-DjoEUkGMX_ST7WnYQpJzDURbUhmFqdQXiO2WzeMSds7xvU75kGmpNAxskH5MkWNuCF3XFr-SKFSummw9HtKWnk4Tj9A4k1E3bJGZglrKnB4-EZ3HuBWTmR5utvOMSn4bGUgMG89xM8UfBJONdZrOcE1QxX7N5mR4E6DiCl35JjSsmLQRlbYHMM0Kj9UrWHpbo8ST992MNZC1va45tZzYGAafL1iamulM",
            189,
            25
        )
    )
    
    val trendingTags = listOf("#SummerMakeup", "#NewArrivals", "#SkincareTips", "#BeautyHacks", "#MakeupTutorial")

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text("Community", fontWeight = FontWeight.Bold) },
                    actions = {
                        IconButton(onClick = { /* Create post */ }) {
                            Icon(Icons.Default.Add, contentDescription = "Create Post")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title, fontWeight = FontWeight.Bold) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(posts) { post ->
                PostCard(post = post)
                HorizontalDivider()
            }
            
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Trending",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        trendingTags.take(3).forEach { tag ->
                            SuggestionChip(
                                onClick = { /* Filter by tag */ },
                                label = { Text(tag) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostCard(post: CommunityPost) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = post.userAvatar,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(post.userName, fontWeight = FontWeight.Bold)
                Text(
                    post.timeAgo,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        Text(post.content)
        
        if (post.imageUrl != null) {
            Spacer(modifier = Modifier.height(12.dp))
            AsyncImage(
                model = post.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* Toggle like */ }
                ) {
                    Icon(
                        if (post.isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (post.isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${post.likes}", style = MaterialTheme.typography.bodySmall)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Comments")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${post.comments}", style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = { /* Save post */ }) {
                Icon(Icons.Default.BookmarkBorder, contentDescription = "Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommunityFeedScreenPreview() {
    MaterialTheme {
        CommunityFeedScreen(navController = rememberNavController())
    }
}
