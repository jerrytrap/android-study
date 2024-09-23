package com.sample.doitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val idEditText: EditText = findViewById(R.id.editText_id)
        val passwordEditText: EditText = findViewById(R.id.editText_password)
        val loginButton: Button = findViewById(R.id.button_login)

        loginButton.setOnClickListener {
            if (idEditText.text.isEmpty() || passwordEditText.text.isEmpty()) {
                Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
}