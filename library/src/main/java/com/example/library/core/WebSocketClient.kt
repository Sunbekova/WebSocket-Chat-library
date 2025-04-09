package com.example.library.core

import okhttp3.*
import okio.ByteString

class WebSocketClient (
    private val url: String,
    private val listener: ChatWebSocketListener
) {
    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    fun connect() {
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onMessage(ws: WebSocket, text: String) {
                listener.onTextReceived(text)
            }

            override fun onMessage(ws: WebSocket, bytes: ByteString) {
                listener.onTextReceived(bytes.utf8())
            }

            override fun onFailure(ws: WebSocket, t: Throwable, response: Response?) {
                listener.onError(t)
            }
        })
    }

    fun send(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Closed by client")
    }
}