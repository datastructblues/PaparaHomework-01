package com.example.hw01_papara.presentation.meals

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsScreen(
    navigateToDetail: (Int) -> Unit,
    mealsViewModel: MealsViewModel = hiltViewModel()
) {
    val mealState by mealsViewModel.mealState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Meals") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            when(mealState) {
                is MealState.Loading -> {
                    // Loading
                    Log.d("MealsScreen", "MealState.Loading")

                }
                is MealState.Success -> {
                    Log.d("MealsScreen", "MealState.Success")
                    val meals = (mealState as MealState.Success).meals.results
                    MealsContent(
                        meals = meals,
                        navigateToDetail = { id -> navigateToDetail(id) }
                    )
                }
                is MealState.Error -> {
                    val message = (mealState as MealState.Error).message
                    Log.d("MealsScreen", "MealState.Error - $message")

                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

        }
    }



}