package com.example.websocketchatlibrary.core

interface ChatWebSocketListener {
    fun onTextReceived(message: String)
    fun onError(error: Throwable)
}