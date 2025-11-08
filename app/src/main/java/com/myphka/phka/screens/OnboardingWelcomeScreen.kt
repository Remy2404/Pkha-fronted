package com.myphka.phka.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myphka.phka.R
import com.myphka.phka.ui.theme.BackgroundLight
import com.myphka.phka.ui.theme.DeepPink
import com.myphka.phka.ui.theme.OnPrimary
import com.myphka.phka.ui.theme.PrimaryLight
import com.myphka.phka.ui.theme.TextPrimary
import com.myphka.phka.ui.theme.TextTertiary
import kotlinx.coroutines.launch

data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

val onboardingPages = listOf(
    OnboardingPage(
        R.drawable.categories,
        "Welcome",
        "Discover beauty, simplified. Your journey to radiant skin starts here."
    ),
    OnboardingPage(R.drawable.beauty_tip_074, "Find Your Match", "Personalized recommendations just for you. Let us help you find the perfect products."),
    OnboardingPage(R.drawable.store_041, "Easy & Secure", "Shop your favorite beauty products with a seamless and secure checkout process.")
)


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingWelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Phka",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                color = TextPrimary
            )
            IconButton(onClick = { navController.navigate("login") }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = TextPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        val pagerState = rememberPagerState { onboardingPages.size }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            val currentPage = onboardingPages[page]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(300.dp)
                        .background(
                            PrimaryLight,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(currentPage.imageRes),
                        contentDescription = "Onboarding Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = currentPage.title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = currentPage.description,
                    fontSize = 16.sp,
                    color = TextTertiary,
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                if (index == pagerState.currentPage) DeepPink else DeepPink.copy(
                                    alpha = 0.2f
                                ),
                                shape = RoundedCornerShape(50)
                            )
                    )
                    if (index < pagerState.pageCount - 1) Spacer(modifier = Modifier.width(8.dp))
                }
            }
            val coroutineScope = rememberCoroutineScope()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepPink.copy(alpha = 0.1f)
                    )
                ) {
                    Text(
                        text = "Skip",
                        color = DeepPink,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Button(
                    onClick = {
                        if (pagerState.currentPage < pagerState.pageCount - 1) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            navController.navigate("login")
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepPink
                    )
                ) {
                    Text(
                        text = if (pagerState.currentPage < pagerState.pageCount - 1) "Next" else "Get Started",
                        color = OnPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun OnboardingWelcomeScreenPreview() {
    OnboardingWelcomeScreen(navController = androidx.navigation.compose.rememberNavController())
}