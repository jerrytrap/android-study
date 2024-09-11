package com.sample.doitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var birthEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addButton: Button
    private lateinit var customers: RecyclerView
    private val adapter = CustomerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setRecyclerView()
        setAddButton()
    }

    private fun initView() {
        nameEditText = findViewById(R.id.editText_name)
        birthEditText = findViewById(R.id.editText_birth)
        phoneEditText = findViewById(R.id.editText_phone)
        addButton = findViewById(R.id.button_add)
        customers = findViewById(R.id.recyclerView)
    }

    private fun setRecyclerView() {
        customers.adapter = adapter
        customers.layoutManager = LinearLayoutManager(this)
    }

    private fun setAddButton() {
        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val birth = birthEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (name.isNotEmpty() && birth.isNotEmpty() && phone.isNotEmpty()) {
                val customer = Customer(name, birth, phone)
                adapter.add(customer)

                clearEditText()
            } else {
                Toast.makeText(this, "내용을 모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText() {
        nameEditText.text = null
        birthEditText.text = null
        phoneEditText.text = null
    }
}