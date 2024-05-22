package com.example.hw01_papara.data.repository

import com.example.hw01_papara.data.model.MealDetailResponse
import com.example.hw01_papara.data.model.MealResponse
import com.example.hw01_papara.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource
): MealRepository {
    override suspend fun getMeals(): Flow<ApiResult<MealResponse>> {
        return remote.getMeals()
    }

    override suspend fun getMealDetails(id: Int): Flow<ApiResult<MealDetailResponse>> {
        return remote.getMealDetails(id)
    }

}