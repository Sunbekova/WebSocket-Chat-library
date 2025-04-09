package com.example.websocketchatlibrary.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.websocketchatlibrary.core.ChatWebSocketListener
import com.example.websocketchatlibrary.core.WebSocketClient
import com.example.websocketchatlibrary.ui.adapter.ChatAdapter
import com.example.websocketchatlibrary.databinding.ActivityMainBinding
import com.example.websocketchatlibrary.ui.model.ChatMessage

internal class ChatActivity : AppCompatActivity(), ChatWebSocketListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatAdapter
    private lateinit var webSocketClient: WebSocketClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChatAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        webSocketClient = WebSocketClient("wss://echo.websocket.org/", this)
        webSocketClient.connect()

        binding.sendButton.setOnClickListener {
            val msg = binding.inputField.text.toString()
            if (msg.isNotBlank()) {
                webSocketClient.send(msg)
                adapter.addMessage(ChatMessage(msg, true))
                binding.inputField.setText("")
            }
        }
    }

    override fun onTextReceived(message: String) {
        runOnUiThread {
            val displayText = if (message == "203 = 0xcb") "Special Message" else message
            adapter.addMessage(ChatMessage(displayText, false))
        }
    }

    override fun onError(error: Throwable) {
        Toast.makeText(this, "Error: ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocketClient.close()
    }
}