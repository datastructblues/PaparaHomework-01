package com.example.hw01_papara.ui.bottombar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hw01_papara.ui.chatnavigate.ChatNavigaitonScreen
import com.example.hw01_papara.ui.chatscreen.MessagingScreen


@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Chat.route
    ) {
        composable(route = BottomBarScreen.Chat.route) {
            MessagingScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Dishes.route) {
            DishesScreen()
        }
        composable(route = BottomBarScreen.Logout.route) {
            LogoutScreen()
        }
    }
}