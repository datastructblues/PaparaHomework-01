package com.example.hw01_papara.presentation.meals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hw01_papara.data.model.Result

@Composable
fun MealsContent(
    meals: List<Result>,
    navigateToDetail: (Int) -> Unit
) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            modifier = Modifier
                .fillMaxHeight(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(count = meals.size) {
                SelectableFoodCard(
                    meal = meals[it],
                    navigateToDetail = { id -> navigateToDetail(id)}
                )
            }
        }
}