package com.example.websocketchatlibrary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.websocketchatlibrary.databinding.ItemMessageReceiverBinding
import com.example.websocketchatlibrary.databinding.ItemMessageSenderBinding
import com.example.websocketchatlibrary.ui.model.ChatMessage

internal class ChatAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val messages = mutableListOf<ChatMessage>()

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isSentByUser) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding = ItemMessageSenderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SenderViewHolder(binding)
        } else {
            val binding = ItemMessageReceiverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ReceiverViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        if (holder is SenderViewHolder) {
            holder.binding.messageText.text = message.content
        } else if (holder is ReceiverViewHolder) {
            holder.binding.messageText.text = message.content
        }
    }

    override fun getItemCount() = messages.size

    fun addMessage(message: ChatMessage) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    class SenderViewHolder(val binding: ItemMessageSenderBinding) : RecyclerView.ViewHolder(binding.root)
    class ReceiverViewHolder(val binding: ItemMessageReceiverBinding) : RecyclerView.ViewHolder(binding.root)
}