package com.example.hw01_papara.data

import android.graphics.Bitmap

data class Message(
    val text: String,
    val isUser: Boolean,
    val timestamp: String,
    val image: Bitmap? = null
)
