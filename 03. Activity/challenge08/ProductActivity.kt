package com.sample.doitandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val menuButton: Button = findViewById(R.id.button_menu)
        val loginButton: Button = findViewById(R.id.button_login)

        menuButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("menu", "상품 관리")
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