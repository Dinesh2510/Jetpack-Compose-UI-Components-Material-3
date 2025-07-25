package com.app.composestorage.deeplink

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    /*https://pixeldev.in/refrel/ABC123
    https://pixeldev.in/productData/12345*/
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onNavigate = {
                navController.navigate(it) {
                    popUpTo("splash") { inclusive = true } // This removes splash from backstack
                }
            })
        }

        composable(
            route = "referral/{code}",
            arguments = listOf(navArgument("code") {
                type = NavType.StringType
                nullable = true
            }),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://pixeldev.in/refrel/{code}"
                }
            )

        ) { backStackEntry ->
            val code = backStackEntry.arguments?.getString("code")
            ReferralScreen(code)
        }

        composable(
            "product/{productId}",
            arguments = listOf(navArgument("productId") {
                type = NavType.StringType
                nullable = true
            }),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://pixeldev.in/productData/{productId}"
                }
            )) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            ProductDetailScreen(productId)
        }
    }
}
