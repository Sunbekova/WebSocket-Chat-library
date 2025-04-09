package com.example.library.core

interface ChatWebSocketListener {
    fun onTextReceived(message: String)
    fun onError(error: Throwable)
}