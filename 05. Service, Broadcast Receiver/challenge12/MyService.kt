package com.sample.doitandroid

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            val text = intent.getStringExtra("text")
            val broadcast = Intent(this, MyReceiver::class.java)
            broadcast.putExtra("text", text)
            sendBroadcast(broadcast)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}