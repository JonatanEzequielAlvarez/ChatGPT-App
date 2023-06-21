package com.creardev.chatgptapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    //https://api.openai.com/v1/chat/completions
    @POST("chat/completions")
    @Headers("Content-Type: application/json")
    fun getChatCompletion(
        @Header("Authorization") apiKey: String,
        @Body requestBody: ChatRequest
    ): Call<ChatResponse>
}