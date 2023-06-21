package com.creardev.chatgptapp

data class ChatRequest(
    val messages: List<ChatMessage>,
    val model: String = "gpt-3.5-turbo",
    val max_tokens: Int = 450
)

data class ChatMessage(
    val role: String,
    val content: String
)

data class ChatResponse(
    val id: String,
    val created: Long,
    val model: String,
    val choices: List<ChatChoice>
)

data class ChatChoice(
    val message: ChatMessage,
    val finish_reason: String,
    val index: Int
)
