package com.myphka.phka.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myphka.phka.ui.theme.BackgroundLight
import com.myphka.phka.ui.theme.DeepPink
import com.myphka.phka.ui.theme.OnPrimary
import com.myphka.phka.ui.theme.TextPrimary

@Composable
fun RegisterScreen(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val termsAccepted = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextPrimary
                )
            }
            Text(
                text = "Register",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.1f))
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DeepPink.copy(alpha = 0.2f),
                    focusedBorderColor = DeepPink,
                    unfocusedLabelColor = TextPrimary.copy(alpha = 0.5f),
                    focusedLabelColor = DeepPink
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DeepPink.copy(alpha = 0.2f),
                    focusedBorderColor = DeepPink,
                    unfocusedLabelColor = TextPrimary.copy(alpha = 0.5f),
                    focusedLabelColor = DeepPink
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DeepPink.copy(alpha = 0.2f),
                    focusedBorderColor = DeepPink,
                    unfocusedLabelColor = TextPrimary.copy(alpha = 0.5f),
                    focusedLabelColor = DeepPink
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DeepPink.copy(alpha = 0.2f),
                    focusedBorderColor = DeepPink,
                    unfocusedLabelColor = TextPrimary.copy(alpha = 0.5f),
                    focusedLabelColor = DeepPink
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = termsAccepted.value,
                    onCheckedChange = { termsAccepted.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = DeepPink,
                        uncheckedColor = DeepPink.copy(alpha = 0.5f)
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "I agree to the ",
                    fontSize = 12.sp,
                    color = TextPrimary.copy(alpha = 0.7f)
                )
                TextButton(
                    onClick = { },
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text(
                        text = "Terms and Conditions",
                        fontSize = 12.sp,
                        color = DeepPink,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DeepPink
                ),
                enabled = termsAccepted.value
            ) {
                Text(
                    text = "Register",
                    color = OnPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 12.sp,
                    color = TextPrimary.copy(alpha = 0.7f)
                )
                TextButton(onClick = { navController.navigate("login") }) {
                    Text(
                        text = "Login",
                        fontSize = 12.sp,
                        color = DeepPink,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
