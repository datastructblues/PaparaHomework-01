package com.example.hw01_papara.navigation

sealed class Screens(val route: String) {

    data object MealsScreen : Screens("meals_screen_route")

    data object MealDetailScreen : Screens("meal_detail_screen_route/{mealId}") {
        fun passMealId(mealId: Int): String {
            return "meal_detail_screen_route/$mealId"
        }
    }
}