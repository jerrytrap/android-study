package com.sample.doitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton: Button = findViewById(R.id.button_login)
        loginButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivityForResult(intent, MENU_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MENU_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val menuName = data?.getStringExtra("selectedMenu")
                if (menuName != null) {
                    Toast.makeText(this, menuName, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val MENU_REQUEST_CODE = 100
    }
}