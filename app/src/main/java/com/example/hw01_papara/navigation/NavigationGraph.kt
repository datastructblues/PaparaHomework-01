package com.example.hw01_papara.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hw01_papara.presentation.detail.DetailScreen
import com.example.hw01_papara.presentation.meals.MealsScreen
import com.example.hw01_papara.utils.Constants.MEAL_ID_ARGUMENT_KEY

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.MealsScreen.route) {
        composable(Screens.MealsScreen.route) {
             MealsScreen(
                navigateToDetail = {
                    navController.navigate(Screens.MealDetailScreen.passMealId(it))
                }
             )
        }

        composable(
            Screens.MealDetailScreen.route,
            arguments = listOf(navArgument(MEAL_ID_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ) {
             DetailScreen(
                 onBackMealsScreen = {
                     navController.popBackStack()
                 }
             )
        }
    }
}