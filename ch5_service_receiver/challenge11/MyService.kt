package com.sample.doitandroid

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            val text = intent.getStringExtra("text")
            sendText(text)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun sendText(text: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("text", text)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK
                .or(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .or(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )

        startActivity(intent)
    }
}