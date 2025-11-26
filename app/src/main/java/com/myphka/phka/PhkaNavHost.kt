package com.myphka.phka

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myphka.phka.screens.LoginScreen
import com.myphka.phka.screens.OnboardingCategoriesScreen
import com.myphka.phka.screens.OnboardingFeaturesScreen
import com.myphka.phka.screens.OnboardingWelcomeScreen
import com.myphka.phka.screens.RegisterScreen
import com.myphka.phka.screens.SplashScreen
import com.myphka.phka.screens.HomeScreen

@Composable
fun PhkaNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("onboarding_welcome") {
            OnboardingWelcomeScreen(navController = navController)
        }
        composable("onboarding_categories") {
            OnboardingCategoriesScreen(navController = navController)
        }
        composable("onboarding_features") {
            OnboardingFeaturesScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("forgot_password") {
            // Placeholder for Forgot Password screen
            LoginScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
    }
}
