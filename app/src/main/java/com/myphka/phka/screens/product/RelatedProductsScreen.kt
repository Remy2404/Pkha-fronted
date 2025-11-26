package com.myphka.phka.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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

data class RelatedProduct(
    val id: String,
    val name: String,
    val price: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelatedProductsScreen(
    navController: NavController,
    productId: String
) {
    // Mock Data
    val frequentlyBoughtTogether = listOf(
        RelatedProduct("1", "Hydrating Cleanser", "$25", "https://lh3.googleusercontent.com/aida-public/AB6AXuCiqxDol5q6h5V2XCDU3eFJ1D9CXuZvslhsarMvBZVB9HvYar9SomZyKYK-BUBYOudFYOMiPpt3BKrref-QfjESI2CSH3BJe81yIRevCctlleX56qoW96JsQttSHXXl8Xf0KiG64tYkFsqfw4LontzwEIobivLS7dqq07mqSbH8ZKl56C3u-vogIZOtx1Ywo9L8RY3apTcx_QK_Xynb-KgfB2FLGk91bTPc-rD_Adyj9awvRu4xlKCv9gAkphcC12PfPrkB8wGCKok"),
        RelatedProduct("2", "Daily Moisturizer", "$30", "https://lh3.googleusercontent.com/aida-public/AB6AXuD0nddAoI2CT87YWzCTZTLs_hbMrn6zaeB9YGK0nA6s2QBFsG87u-U4MCZAZFXC-t22_Wy1nXPI5uvxwpqKI-IEenKWGUGXQhxWDqY5LgRLaAf6zPpyseL34kK8vGSWQ1zMtX4_Ijpy01UMiycC67iBrC8WjfEoHHtA7dzrL505Q98H1BwEUti6Nplq1t0vM3U0HndJSfkqew7cbkMLh2P8eqdbetjuvxvfx7-HUV2V4NeK954EEG3mP5evjFV5ccdDwnqExYdYzYo"),
        RelatedProduct("3", "Sunscreen SPF 30", "$20", "https://lh3.googleusercontent.com/aida-public/AB6AXuAUAziFy5AzaDvTL2i8h_4oholl1qe2VDyVZ-_Jxy4kPNQYmT5jtJkbOSzjEhHiz3JsphhlccF3VKx8v0MWcU0ic3TDhmRvn4Gsl1WdKLbSPZ7LaiMGRvn1y6nhej49eBG7cgs50OMDPpjC83wq124JHkJys01Yc_l2HzBI8koudXnzLhuS59o0QQK_DkcSQRLcmM1GUbtKOa0kjZmg5XgXoeQdxesc1Rs_qj4piIGdeQssbb8dxn9V60VXET3oYKqP3vo5EB9uc4A")
    )

    val similarItems = listOf(
        RelatedProduct("4", "Gentle Exfoliating Scrub", "$22", "https://lh3.googleusercontent.com/aida-public/AB6AXuDFq6aFpIEMizpcBilgDKGqZKIJ_hkRSJCM9wqI3riQ5aB1zEnSb0eZV167Uoe9kfYn7jcbhFWFCcKjy7hdShS7VDxlwQk2SZETXgA2V_ANl_8IYq7rYAQnNcjq6DUJQ07jCFk_Mj3DDajiO-cuB1MlpD9Db3q0cuqnIsAJzaN6hSuBFKVdZW1GevbMsDnG0Ixdx4LcXoWhdVwR1HQNaowFwHl047U6RoOwnfVEPt2nXlhFI79i8GfSWxDM58armlmHd_DDoFMBuZc"),
        RelatedProduct("5", "Night Repair Serum", "$35", "https://lh3.googleusercontent.com/aida-public/AB6AXuAEMuAzmsCwa1DZWVG-dqBBpDGhuL062_b5isG7IMnhQk7o-VdOfxu_YGXDG8_V722XT-fOOnYUdahJWXUsUVwyxSJY8ABwzG4IuTSC4OukwwWign_1ZmnUhFY3w5FC07iRWubtAPPdMJJEA6NNVBNANu8fPx0KroCKes6tuZUehC-oOZukur4MMlu7u5TPng3KSnRddnzyZiE5fBzS0OF9LhMP-aLi8kyWJeMv_vrz3pMrAIXdofRM_zvB2uPf2ILMl-K4TLMHEa4"),
        RelatedProduct("6", "Eye Cream", "$28", "https://lh3.googleusercontent.com/aida-public/AB6AXuBBSYAogfH7bloTqVDqrSkEVHJKz9Y7v8MOZAPJpOrHgAxZTHSUqWv1zJhOLBrf-bFw8xwXitREey--_lcyoC7szsL_us3ZRIQyjbQLofeG11aKD8ZOvuAioXW-zd7VXRjmtZVBLUgcNl421Pu2-WzX5V36x0Bt724pVOfnPSbTaWbJW8HtqFHY1Rq3JwtY_kCrc7xV9IM04F_mf6xR7AGtXDCqAXx372kABYypvMVTdeZtEQPWpXcUWngM2ddc85PqgUkWV6-qz0Y")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Complete Your Routine", fontWeight = FontWeight.Bold) },
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
            // Frequently Bought Together
            ProductSection(
                title = "Frequently Bought Together",
                products = frequentlyBoughtTogether,
                onProductClick = { navController.navigate("product_detail/${it.id}") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Similar Items
            ProductSection(
                title = "Similar Items",
                products = similarItems,
                onProductClick = { navController.navigate("product_detail/${it.id}") }
            )
        }
    }
}

@Composable
fun ProductSection(
    title: String,
    products: List<RelatedProduct>,
    onProductClick: (RelatedProduct) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                RelatedProductItem(product = product, onClick = { onProductClick(product) })
            }
        }
    }
}

@Composable
fun RelatedProductItem(
    product: RelatedProduct,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clickable(onClick = onClick)
    ) {
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
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            maxLines = 2
        )
        Text(
            text = product.price,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}
