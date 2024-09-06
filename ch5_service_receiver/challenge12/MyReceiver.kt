package com.sample.doitandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val text = intent.getStringExtra("text")
        sendText(context, text)
    }

    private fun sendText(context: Context, text: String?) {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("text", text)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK
                .or(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .or(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )

        context.startActivity(intent)
    }
}