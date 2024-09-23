package com.sample.doitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var products: RecyclerView
    private val adapter = ProductAdapter()
    val sampleData = listOf(
        Product(
            "롱코트", "160,000원", "명절 기획상품"
        ),
        Product(
            "빈탄 와이셔츠", "80,000원", "특가상품"
        ),
        Product(
            "조깅화", "220,000원", "한정판매"
        ),
        Product(
            "구구 썬글라스", "500,000원", "명품"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        products = findViewById(R.id.recyclerView)
        setRecyclerView()
        adapter.addAll(sampleData)
    }

    private fun setRecyclerView() {
        products.adapter = adapter
        products.layoutManager = GridLayoutManager(this, 2)

        adapter.setOnItemClickListener(object : ProductAdapter.OnItemClickListener {
            override fun onItemClick(product: Product) {
                Toast.makeText(
                    this@MainActivity,
                    "이름: ${product.name}, 가격: ${product.price}, 설명: ${product.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}