package com.example.hw01_papara.ui.chatscreen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw01_papara.data.Message
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class ChatScreenViewModel : ViewModel() {

    fun createGenerativeModel(modelName: String) = GenerativeModel(
        modelName = modelName,
        apiKey = "AIzaSyBD2qldNoHxUuzKxj3R7A1qP25-0QXUbgc",
        safetySettings = safetySettings
    )

    private val harassmentSafety = SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.ONLY_HIGH)
    private val sexualContentSafety = SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.ONLY_HIGH)
    private val hateSpeechSafety = SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE)

    private val generativeModelVision by lazy { createGenerativeModel("gemini-pro-vision") }
    private val generativeModelText by lazy { createGenerativeModel("gemini-pro") }

    private val safetySettings by lazy {
        listOf(
            harassmentSafety,
            sexualContentSafety,
            hateSpeechSafety
        )
    }

    var messages by mutableStateOf(listOf<Message>())


    fun sendMessage(message: String, image: Bitmap? = null) = viewModelScope.launch {
        messages = messages + Message(message, true, getTimestamp(), image)
    }

    fun receiveMessage() = viewModelScope.launch {
        val newMessage = messages.last().text
        val response = generativeModelText.generateContent(
            prompt = newMessage
        )
        if (response.text != null) {
            messages = messages + Message(response.text!!, false, getTimestamp())
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimestamp(): String {
        var sdf = SimpleDateFormat("HH:mm aa")
        return sdf.format(Date())
    }

    fun recieveImageResponse(bitmap: Bitmap) = viewModelScope.launch {
        val inputContent = content {
            image(bitmap)
            text(messages.last().text)
        }

        val response = generativeModelVision.generateContent(
            inputContent
        )
        if (response.text != null) {
            messages = messages + Message(response.text!!, false, getTimestamp())
        }
    }
}