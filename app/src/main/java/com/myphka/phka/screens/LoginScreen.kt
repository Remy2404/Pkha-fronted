package com.myphka.phka.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myphka.phka.R
import com.myphka.phka.ui.theme.*
import com.myphka.phka.viewmodels.LoginViewModel


@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    OnboardingBoxBackground,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Phka Logo",
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(12.dp)),
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { viewModel.updateEmail(it) },
                label = { Text("Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email",
                        tint = IconTint
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("login_email_field"),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DeepPink.copy(alpha = 0.2f),
                    focusedBorderColor = DeepPink,
                    unfocusedLabelColor = IconTint,
                    focusedLabelColor = DeepPink
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.updatePassword(it) },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Password",
                        tint = IconTint
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("login_password_field"),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DeepPink.copy(alpha = 0.2f),
                    focusedBorderColor = DeepPink,
                    unfocusedLabelColor = IconTint,
                    focusedLabelColor = DeepPink
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Test helper: fill test credentials quickly
            TextButton(onClick = { viewModel.updateEmail("test@example.com"); viewModel.updatePassword("password123") }) {
                Text(text = "Fill test creds", fontSize = 12.sp, color = IconTint)
            }

            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.updatePassword(it) },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Password",
                        tint = IconTint
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("login_password_field"),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DeepPink.copy(alpha = 0.2f),
                    focusedBorderColor = DeepPink,
                    unfocusedLabelColor = IconTint,
                    focusedLabelColor = DeepPink
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(16.dp))

            uiState.error?.let { err ->
                Text(text = err, color = androidx.compose.ui.graphics.Color.Red, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { navController.navigate("forgot_password") }) {
                    Text(
                        text = "Forgot Password?",
                        fontSize = 12.sp,
                        color = DeepPink,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("login_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DeepPink
                )
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(color = White, modifier = Modifier.size(18.dp))
                } else {
                    Text(
                        text = "Login",
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Or login with",
                fontSize = 12.sp,
                color = IconTint
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { viewModel.loginWithGoogle() },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepPink.copy(alpha = 0.1f)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_google),
                        contentDescription = "Google Icon",
                        modifier = Modifier.requiredSize(20.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Google",
                        color = DarkText,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                }
                Button(
                    onClick = { viewModel.loginWithApple() },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepPink.copy(alpha = 0.1f)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_apple),
                        contentDescription = "Apple Icon",
                        modifier = Modifier.requiredSize(20.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Apple",
                        color = DarkText,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontSize = 12.sp,
                    color = IconTint
                )
                TextButton(onClick = { navController.navigate("register") }) {
                    Text(
                        text = "Create Account",
                        fontSize = 12.sp,
                        color = DeepPink,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
     LoginScreen(navController = rememberNavController())
}