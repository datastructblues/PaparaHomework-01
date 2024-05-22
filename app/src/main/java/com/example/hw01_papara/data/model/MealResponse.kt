package com.example.hw01_papara.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MealResponse(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)