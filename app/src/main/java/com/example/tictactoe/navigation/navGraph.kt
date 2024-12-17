package com.example.tictactoe.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tictactoe.pages.GamePage
import com.example.tictactoe.pages.PlayerPage

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navHostController, startDestination = NavigationItem.PlayerPage.route) {
        composable(NavigationItem.PlayerPage.route) {
            PlayerPage(navHostController)
        }
        composable(
            route = NavigationItem.GamePage.route,
            arguments = listOf(
                navArgument("player1") { type = NavType.StringType },
                navArgument("player2") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val player1 = backStackEntry.arguments?.getString("player1")
            val player2 = backStackEntry.arguments?.getString("player2")

            GamePage(navHostController, player1, player2)
        }
    }
}