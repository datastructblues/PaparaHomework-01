package com.example.hw01_papara.data.repository

import com.example.hw01_papara.data.api.MealApi
import com.example.hw01_papara.data.model.MealDetailResponse
import com.example.hw01_papara.data.model.MealResponse
import com.example.hw01_papara.utils.ApiResult
import com.example.hw01_papara.utils.apiFlow
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val mealApi: MealApi
): RemoteDataSource {
    override suspend fun getMeals(): Flow<ApiResult<MealResponse>> {
        return apiFlow { mealApi.getAllMeals() }
    }

    override suspend fun getMealDetails(id: Int): Flow<ApiResult<MealDetailResponse>> {
        return apiFlow { mealApi.getMealDetails(id) }
    }

}