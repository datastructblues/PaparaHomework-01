package com.example.hw01_papara.data.repository

import com.example.hw01_papara.data.model.MealDetailResponse
import com.example.hw01_papara.data.model.MealResponse
import com.example.hw01_papara.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    suspend fun getMeals(): Flow<ApiResult<MealResponse>>


    suspend fun getMealDetails(id: Int): Flow<ApiResult<MealDetailResponse>>
}