package com.example.hw01_papara.presentation.detail

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hw01_papara.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    onBackMealsScreen: () -> Unit
) {

    val mealDetailState by detailsViewModel.selectedMeal.collectAsState()
    


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Meal Detail") },
                navigationIcon = {
                    IconButton(onClick = { onBackMealsScreen() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(
                            R.string.back_to_meals
                        )
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when (mealDetailState) {
            is MealDetailState.Loading -> {
                // Loading
                CircularProgressIndicator()
            }
            is MealDetailState.Success -> {
                val meal = (mealDetailState as MealDetailState.Success).meals
                DetailsContent(
                    meal = meal,
                    paddingValues = paddingValues
                )
            }
            is MealDetailState.Error -> {
                Log.d("DetailScreen", "MealDetailState.Error - ${(mealDetailState as MealDetailState.Error).message}")
                Text(text = (mealDetailState as MealDetailState.Error).message)
            }
        }
    }





}
