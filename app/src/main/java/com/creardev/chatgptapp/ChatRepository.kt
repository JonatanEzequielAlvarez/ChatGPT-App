package com.creardev.chatgptapp

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChatRepository {
    private val apiService = ApiClient.create()

     fun getChatCompletion(apiKey: String, messages: List<ChatMessage>, completionCallback: (String) -> Unit) {
        val chatRequest = ChatRequest(messages = messages)

        apiService.getChatCompletion(apiKey, chatRequest).enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                if (response.isSuccessful) {
                    val chatResponse = response.body()
                    val completion = chatResponse?.choices?.firstOrNull()?.message?.content
                    completion?.let { completionCallback.invoke(it) }
                } else {
                    Log.d("error-api", response.message())
                }
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                // Handle network error
            }
        })
    }
}