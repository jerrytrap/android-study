package com.sample.doitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val customerButton: Button = findViewById(R.id.button_customer)
        val salesButton: Button = findViewById(R.id.button_sales)
        val productButton: Button = findViewById(R.id.button_product)

        customerButton.setOnClickListener {
            val intent = Intent(this, CustomerActivity::class.java)
            startActivityForResult(intent, SUB_REQUEST_CODE)
        }
        salesButton.setOnClickListener {
            val intent = Intent(this, SalesActivity::class.java)
            startActivityForResult(intent, SUB_REQUEST_CODE)
        }
        productButton.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivityForResult(intent, SUB_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SUB_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val menuName = data?.getStringExtra("menu")
                if (menuName != null) {
                    Toast.makeText(this, menuName, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val SUB_REQUEST_CODE = 200
    }
}