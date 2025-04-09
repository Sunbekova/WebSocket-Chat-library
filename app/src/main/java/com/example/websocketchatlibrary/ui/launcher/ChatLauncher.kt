package com.example.websocketchatlibrary.ui.launcher
import android.content.Context
import android.content.Intent
import com.example.websocketchatlibrary.ui.ChatActivity

object ChatLauncher {
    fun start(context: Context) {
        val intent = Intent(context, ChatActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}