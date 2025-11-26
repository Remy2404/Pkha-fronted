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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductIngredientsScreen(
    navController: NavController,
    productId: String
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ingredients", fontWeight = FontWeight.Bold) },
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
                .padding(16.dp)
        ) {
            // Key Ingredients
            Text(
                text = "Key Ingredients",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            KeyIngredientItem(
                name = "Green Tea Extract",
                description = "Antioxidant, Anti-inflammatory",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDStmoiI0M8Rc_vne2uoE2debsqEAYQZLnUHVS6s1RlQCM1P1wVn2B-RLDJo_tPkZbuCsJwU2lpZZfegg23nDLpalKfH-x28P8foRH8cEi7ZRY_5d7THt2BDtFqj98MfOrsFm0d72DHiBQR4sC1nDoXjEzEKFrTXFRA-CzDfSYT3y6KB9XowV4KkK5HE-JHHsMjuDxGXzXYftDL9dZkP87faRd5Ex8YmdTv_9-yHp6Blp3OFany2t31cEUUzuZlyuJvQ8tYgFVN2Ts"
            )
            Spacer(modifier = Modifier.height(16.dp))
            KeyIngredientItem(
                name = "Aloe Vera",
                description = "Moisturizing, Soothing",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD53y-O9vGUankaTkiRbLaXbFcdfxNoGPW2BTanvpDZjcjRanzbMub5AypjK_pYlvxZBDRdkjnh3CMwV16rd5Rg3rKVBSshudPDi2d2io74DEmt9zoPdMwCll5jYXZ7rQvCxQxg1Z2gkFo--fVWj9-IrHoc3T8dUV8oJbPeT2JiuD2UaYCFJ4reX0tJYwlZCZqkr47hGhDX-EXAb69w8zz2tE-E1PxgRsv0EoMwxW_HbT66bxV_pPYDtJceMFLYVFWA0qfkK8nFvYM"
            )
            Spacer(modifier = Modifier.height(16.dp))
            KeyIngredientItem(
                name = "Ceramides",
                description = "Hydrating, Barrier Repair",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCTcj-IoKcEonOfZ8DgkLsXW9caEyw_6K4xLBDQcwyzbWgY8X_V7R4lQd22TFhMbORlDEAMMZYFpESvYaF-BF8yg_aPhcnLkfz663QFlDg0gigL_HQF55tuvZxMGj478xX2JC9yo-XFWqvcS93ZWF7k1eRrwW9xerOY4-886MeuecMrfpin4O6tu9EgVnD2VlhryehxoA6R12EpiLTWZiCCh2kqg118Qanecxxi5OeZgLT357mN0_SLWdiTQhRk9cerpYa2_bW8dQY"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Full Ingredient List
            Text(
                text = "Full Ingredient List",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Water, Glycerin, Butylene Glycol, Camellia Sinensis Leaf Extract, Aloe Barbadensis Leaf Juice, Ceramide NP, Sodium Hyaluronate, Panthenol, Allantoin, Tocopherol, Caprylic/Capric Triglyceride, Hydrogenated Lecithin, Phytosterols, Carbomer, Arginine, Phenoxyethanol, Ethylhexylglycerin",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Safety Information
            Text(
                text = "Safety Information",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "For external use only. Avoid contact with eyes. If irritation occurs, discontinue use. Keep out of reach of children.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
fun KeyIngredientItem(
    name: String,
    description: String,
    imageUrl: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
