package com.example.hw01_papara.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
)