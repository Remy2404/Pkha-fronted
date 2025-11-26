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
fun TermsOfServiceScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Terms of Service", fontWeight = FontWeight.Bold) },
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
                title = "1. Acceptance of Terms",
                content = "By accessing and using this application, you accept and agree to be bound by the terms and provision of this agreement."
            )
            
            PolicySection(
                title = "2. Use License",
                content = "Permission is granted to temporarily download one copy of the materials on Phka's application for personal, non-commercial transitory viewing only."
            )
            
            PolicySection(
                title = "3. Disclaimer",
                content = "The materials on Phka's application are provided on an 'as is' basis. Phka makes no warranties, expressed or implied, and hereby disclaims and negates all other warranties."
            )
            
            PolicySection(
                title = "4. Limitations",
                content = "In no event shall Phka or its suppliers be liable for any damages arising out of the use or inability to use the materials on Phka's application."
            )
            
            PolicySection(
                title = "5. Revisions",
                content = "The materials appearing on Phka's application could include technical, typographical, or photographic errors. Phka does not warrant that any of the materials on its application are accurate, complete, or current."
            )
            
            PolicySection(
                title = "6. Governing Law",
                content = "These terms and conditions are governed by and construed in accordance with the laws and you irrevocably submit to the exclusive jurisdiction of the courts in that location."
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TermsOfServiceScreenPreview() {
    MaterialTheme {
        TermsOfServiceScreen(navController = rememberNavController())
    }
}
