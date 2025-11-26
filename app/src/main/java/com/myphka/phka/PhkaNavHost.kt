package com.myphka.phka

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.myphka.phka.screens.*
import com.myphka.phka.screens.cart.ShoppingCartScreen
import com.myphka.phka.screens.checkout.*
import com.myphka.phka.screens.orders.*
import com.myphka.phka.screens.search.*
import com.myphka.phka.screens.wishlist.CompareProductsScreen
import com.myphka.phka.screens.wishlist.WishlistScreen
import com.myphka.phka.screens.product.*
import com.myphka.phka.screens.cart.*
import com.myphka.phka.screens.profile.*
import com.myphka.phka.screens.search.*
import com.myphka.phka.screens.quiz.*
import com.myphka.phka.screens.tryon.*

@Composable
fun PhkaNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = modifier
    ) {
        // Onboarding & Auth
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
            ForgotPasswordScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }

        // Product Discovery
        composable("category_list") {
            CategoryListScreen(navController = navController)
        }
        composable(
            "product_list/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            ProductListScreen(navController = navController, categoryId = categoryId)
        }
        composable(
            "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(navController = navController, productId = productId)
        }
        composable(
            "product_reviews/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductReviewsScreen(navController = navController, productId = productId)
        }
        composable(
            "product_variants/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductVariantsScreen(navController = navController, productId = productId)
        }
        composable(
            "product_ingredients/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductIngredientsScreen(navController = navController, productId = productId)
        }
        composable(
            "how_to_use/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            HowToUseScreen(navController = navController, productId = productId)
        }
        composable(
            "related_products/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            RelatedProductsScreen(navController = navController, productId = productId)
        }
        composable(
            "share_product/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ShareProductScreen(navController = navController, productId = productId)
        }

        // Search
        composable("search") {
            SearchScreen(
                onSearchSubmitted = { query -> navController.navigate("search_results") }, // Simplified for now
                onBackClick = { navController.navigateUp() }
            )
        }
        composable("search_results") {
            SearchResultsScreen(
                onProductClick = { productId -> navController.navigate("product_detail/$productId") },
                onFilterClick = { navController.navigate("filter") },
                onSortClick = { navController.navigate("sort") },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable("filter") {
            FilterScreen(
                onApply = { navController.navigateUp() },
                onClose = { navController.navigateUp() }
            )
        }
        composable("advanced_search") {
            AdvancedSearchScreen(navController = navController)
        }
        composable("sort") {
            SortScreen(
                onApply = { navController.navigateUp() },
                onClose = { navController.navigateUp() }
            )
        }

        // Cart & Wishlist
        composable("cart") {
            ShoppingCartScreen(
                onCheckoutClick = { navController.navigate("checkout_address") },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable(
            "cart_item_edit/{cartItemId}",
            arguments = listOf(navArgument("cartItemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val cartItemId = backStackEntry.arguments?.getString("cartItemId") ?: ""
            CartItemEditScreen(navController = navController, cartItemId = cartItemId)
        }
        composable("wishlist") {
            WishlistScreen(
                onProductClick = { productId -> navController.navigate("product_detail/$productId") },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable("compare_products") {
            CompareProductsScreen(
                products = emptyList(), // TODO: Pass products
                onBackClick = { navController.navigateUp() }
            )
        }

        // Checkout
        composable("checkout_address") {
            CheckoutAddressScreen(
                onNextClick = { navController.navigate("checkout_payment") },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable("checkout_payment") {
            CheckoutPaymentScreen(
                onNextClick = { navController.navigate("checkout_review") },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable("checkout_review") {
            CheckoutReviewScreen(
                onOrderPlaced = { orderId -> navController.navigate("order_confirmation/$orderId") },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable(
            "order_confirmation/{orderId}",
            arguments = listOf(navArgument("orderId") { type = NavType.StringType })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
            OrderConfirmationScreen(
                orderId = orderId,
                onContinueShopping = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        // Orders
        composable("order_history") {
            OrderHistoryScreen(
                onOrderClick = { orderId -> navController.navigate("order_details/$orderId") },
                onBackClick = { navController.navigateUp() },
                navController = navController
            )
        }
        composable(
            "order_details/{orderId}",
            arguments = listOf(navArgument("orderId") { type = NavType.StringType })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
            OrderDetailsScreen(
                orderId = orderId,
                onTrackOrderClick = { navController.navigate("order_tracking/$orderId") },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable(
            "order_tracking/{orderId}",
            arguments = listOf(navArgument("orderId") { type = NavType.StringType })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
            OrderTrackingScreen(
                orderId = orderId,
                onBackClick = { navController.navigateUp() }
            ) 
        }

        // Profile & Settings
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }

        // Special Features
        composable("beauty_quiz") {
            BeautyQuizScreen(navController = navController)
        }
        composable("quiz_results") {
            QuizResultsScreen(navController = navController)
        }
        composable("virtual_try_on") {
            VirtualTryOnScreen(navController = navController)
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun PhkaNavHostPreview() {
    PhkaNavHost(navController = androidx.navigation.compose.rememberNavController(), modifier = Modifier)
}
