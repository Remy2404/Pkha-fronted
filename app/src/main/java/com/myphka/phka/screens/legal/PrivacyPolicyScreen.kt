package com.myphka.phka.screens.legal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Privacy Policy", fontWeight = FontWeight.Bold) },
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
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Last updated: November 26, 2024",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            PolicySection(
                title = "1. Information We Collect",
                content = "We collect information you provide directly to us, including your name, email address, shipping address, and payment information when you create an account or make a purchase."
            )
            
            PolicySection(
                title = "2. How We Use Your Information",
                content = "We use the information we collect to process your orders, communicate with you, improve our services, and personalize your shopping experience."
            )
            
            PolicySection(
                title = "3. Information Sharing",
                content = "We do not sell or rent your personal information to third parties. We may share your information with service providers who help us operate our business."
            )
            
            PolicySection(
                title = "4. Data Security",
                content = "We implement appropriate security measures to protect your personal information from unauthorized access, alteration, or disclosure."
            )
            
            PolicySection(
                title = "5. Your Rights",
                content = "You have the right to access, correct, or delete your personal information. You may also opt out of marketing communications at any time."
            )
            
            PolicySection(
                title = "6. Contact Us",
                content = "If you have any questions about this Privacy Policy, please contact us at privacy@phka.com"
            )
        }
    }
}

@Composable
fun PolicySection(title: String, content: String) {
    Column {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyScreenPreview() {
    MaterialTheme {
        PrivacyPolicyScreen(navController = rememberNavController())
    }
}
