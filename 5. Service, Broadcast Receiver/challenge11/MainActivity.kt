package com.sample.doitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        button.setOnClickListener {
            sendText()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            val text = intent.getStringExtra("text")
            textView.text = text
        }
    }

    private fun initView() {
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.text)
    }

    private fun sendText() {
        val text = editText.text.toString()
        val intent = Intent(this, MyService::class.java)
        intent.putExtra("text", text)

        startService(intent)
    }
}