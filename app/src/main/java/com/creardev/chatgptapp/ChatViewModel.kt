package com.creardev.chatgptapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ChatViewModel : ViewModel() {
    private val chatRepository = ChatRepository()
    val chatResponse = MutableLiveData<String>()

    fun getChatCompletion(apiKey: String, messages: List<ChatMessage>) {
        chatRepository.getChatCompletion(apiKey, messages) { completion ->
            chatResponse.postValue(completion)
        }
    }
}