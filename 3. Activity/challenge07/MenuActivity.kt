package com.sample.doitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val customerButton: Button = findViewById(R.id.button_customer)
        val salesButton: Button = findViewById(R.id.button_sales)
        val productButton: Button = findViewById(R.id.button_product)

        customerButton.setOnClickListener {
            setActivityResult(customerButton.text.toString())
        }
        salesButton.setOnClickListener {
            setActivityResult(salesButton.text.toString())
        }
        productButton.setOnClickListener {
            setActivityResult(productButton.text.toString())
        }
    }

    private fun setActivityResult(menuName: String) {
        val intent = Intent()
        intent.putExtra("selectedMenu", menuName)
        setResult(RESULT_OK, intent)
        finish()
    }
}