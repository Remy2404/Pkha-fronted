package com.myphka.phka.screens.support

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
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

data class TutorialVideo(
    val id: String,
    val title: String,
    val duration: String,
    val views: String,
    val thumbnailUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialVideosScreen(navController: NavController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Makeup", "Skincare")
    
    val videos = listOf(
        TutorialVideo(
            "1",
            "Mastering the Perfect Winged Eyeliner",
            "12 min",
            "2.5K views",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuBPb1u7Ih_T3N05rDpsSGGXGVR0wWvLEtWPXh3IhwcsnDxSxxEBZ-rxCC8VYkJF3PELpkfJ17eRqDGJFOYqTFITE-Vhsg5q2xAZP_VdXeMtf6AYehgJBv3vzxz0fQfSYvhs3wKgHlWN6HGvFwSZ7j_jurPB-UsCpuu4s8yUs4blqF28XlQ2vNdG7YBzORGs09bp9ssPSeh8teD-3o9Q4ILW68mcIcGUv3aCJ0i7nLIe2m0QM1um1JTF7FichyNZQOx9Enj4PEKu7kg"
        ),
        TutorialVideo(
            "2",
            "Everyday Glam Makeup Tutorial",
            "15 min",
            "3.1K views",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuAzkhuQ5axmtrAmZsgP7ANRYtx4mSLX488lTKch2ZF-hC5GA1Ub7oU2TfZdzNEvDk79mCH5sM-WtniuzsZ_C6RpA_Zrdr0ZMiadxyF0PRJeCXVvR3C0_HrxTf0RMypzxW3da0aC_4WSVg_0xpyqFZ5MAsW1lOpSoTIpW8iVoVqVhfkqfrf2BtkCgoVOQ8xqFf0Ap0TQuiQZxa_7NgYGT5q-2YwV2OhXgLLs1LDB41PZ3yJwmPVI5JmWQrK3BmLLnAlM91-OqyM0NGM"
        ),
        TutorialVideo(
            "3",
            "Quick & Easy Contouring Guide",
            "10 min",
            "1.8K views",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuBI7lniPqTmEsn3MjNLMOY_DU5h_j4rW80avz0wW64EL8eNFHAt07kiBaNGgOddMaR15pWFy2xp2b3_H7EUTuYfooj6n4Ym89W2V1IlEsqRk52y8ZXuP-LVc38z66tEcvvxJ56y3u-kovPV_TOtw4Sa8VU8SoaGH-MfRRC4jBEU9Qv2h_Gp3u-khN6yYwpVJrbsG9NTOEwFOS2h6yGXhbyHayBRcPjiOtUVNXbZ5A6Xp1C9vOXQufr3W3hmol0bNO9-nUj-eJTlni8"
        ),
        TutorialVideo(
            "4",
            "Smokey Eye Look for Beginners",
            "18 min",
            "4.2K views",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuD0HSubV3YfujShf7LwAnez4PZ6PYD_6CcMZao57qhBIP6y6TTzSTwxQVMGow9CqfAECpZps9FY-Dx9dojFpz5Vqs-bnkecAnVsEe-1q-9AXeUYYcFHy3olOZrNSGo5EyRmu5PlhR03oyqao-1Roc4IeM8z_nsNECcBGbkjiZevprtyZ0sKC6h5qF54PijdRujb-M63bcfhvDHWFrGzqLQBrQoQyd9HELPv79Nf434dfcYVV3h00ypHVljws_Z6kagcBPk-EqUEKmg"
        ),
        TutorialVideo(
            "5",
            "Natural Makeup Look Tutorial",
            "14 min",
            "2.9K views",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuDlU3hU_pY_t2CnxfGi4PbwCB9eKztJkVpYgRlK-vhEizaXKzH-YRHo3pkV3k4zCM9yrOHzFunHktyb9sKrKqAZvBXv3msPOY65igyU0FAn6GsAV4NW01Sc0d-KiMhDPuZ4bTGGn3j6i79nkOEVZdSUMRc5ygWJW2qF68DrCtwizZBEzZIpN7BKze0v8i-W58dSZ4KCZJ2ypZssqOgq-NirgtxNruOy-XhDjm4tBnrV2sRlRQJ-B7TnuhfvcOkZdiDliQOwPNpE39A"
        ),
        TutorialVideo(
            "6",
            "Bold Lip Application Tips",
            "8 min",
            "1.5K views",
            "https://lh3.googleusercontent.com/aida-public/AB6AXuC13mcU0ZSctbW78ud-MTzQGlGS6NDz19zbBURI2RMkMp15vkkCcxwNl7QTYIMBhuLXMhDVMSaw6663m8z8Nvku3o1i5rQYEBa40FUMu5YQfeC4by2hRuX7PnMRvREhGVo47DTxq7BQmaqMzNgtHzWR6CQsDAru9lbrKt4FhZmTNasgal-JHg2IL-UarREG9raTw4_xT-t7iCT9jOqlwYS1vEzY6UNbfFSV4ly2g-HF3U5itn-Q9EIgXKanns8VSWbOwJRXUC1cDfY"
        )
    )

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text("Tutorials", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(videos) { video ->
                VideoCard(video = video)
            }
        }
    }
}

@Composable
fun VideoCard(video: TutorialVideo) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(12.dp))
                .clickable { /* Play video */ }
        ) {
            AsyncImage(
                model = video.thumbnailUrl,
                contentDescription = video.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = video.title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2
        )
        Text(
            text = "${video.duration} · ${video.views}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TutorialVideosScreenPreview() {
    MaterialTheme {
        TutorialVideosScreen(navController = rememberNavController())
    }
}
