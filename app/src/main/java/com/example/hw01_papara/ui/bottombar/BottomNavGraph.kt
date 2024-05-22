package com.example.hw01_papara.ui.bottombar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hw01_papara.presentation.detail.DetailScreen
import com.example.hw01_papara.presentation.meals.MealsScreen
import com.example.hw01_papara.ui.chatscreen.MessagingScreen
import com.example.hw01_papara.utils.Constants.MEAL_ID_ARGUMENT_KEY

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Chat.route
    ) {
        composable(route = BottomBarScreen.Chat.route) {
            Box(modifier = Modifier.padding(bottom = 56.dp)) { // Adjust 56.dp based on the height of your BottomBar
                MessagingScreen(navController = navController)
            }
        }
        composable(route = BottomBarScreen.Dishes.route) {
            Box(modifier = Modifier.padding(bottom = 56.dp)) { // Adjust 56.dp based on the height of your BottomBar
                MealsScreen(navController = navController)
            }
        }

        composable(
            route = BottomBarScreen.Detail.route,
            arguments = listOf(navArgument(MEAL_ID_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ) {
            DetailScreen {
                navController.popBackStack()
            }
        }


        composable(route = BottomBarScreen.Logout.route) {
            Box(modifier = Modifier.padding(bottom = 56.dp)) { // Adjust 56.dp based on the height of your BottomBar
                LogoutScreen()
            }
        }
    }
}
