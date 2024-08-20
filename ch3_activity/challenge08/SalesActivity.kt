package com.sample.doitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SalesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        val menuButton: Button = findViewById(R.id.button_menu)
        val loginButton: Button = findViewById(R.id.button_login)

        menuButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("menu", "매출 관리")
            setResult(RESULT_OK, intent)
            finish()
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}