package com.example.hw01_papara.data.api

import com.example.hw01_papara.data.model.MealDetailResponse
import com.example.hw01_papara.data.model.MealResponse
import com.example.hw01_papara.utils.Constants.API_KEY
import com.example.hw01_papara.utils.Constants.API_KEY2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealApi {

    @GET("recipes/complexSearch")
    suspend fun getAllMeals(
        @Query("number") number: Int = 30,
        @Query("offset") offset: Int = 0,
        @Query("apiKey") apiKey: String = API_KEY2
    ): Response<MealResponse>


    @GET("recipes/{id}/information")
    suspend fun getMealDetails(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = API_KEY2
    ): Response<MealDetailResponse>

}