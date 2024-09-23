package com.sample.doitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var requestButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var retrofit: Retrofit
    private lateinit var service: NetworkService
    private val adapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRetrofit()
        setView()
        setRecyclerView()
        setRequestButton()
    }

    private fun setRetrofit() {
        val module = JacksonXmlModule()
        module.setDefaultUseWrapper(false)

        val mapper = XmlMapper(module)
        mapper.registerKotlinModule()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build()

        service = retrofit.create(NetworkService::class.java)
    }

    private fun setView() {
        requestButton = findViewById(R.id.button_request)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun setRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun setRequestButton() {
        requestButton.setOnClickListener {
            requestData()
        }
    }

    private fun requestData() {
        service.getData()
            .enqueue(object : Callback<Rss> {
                override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                    val newsList = response.body()?.channel?.items?.map { it.toNews() }
                    if (newsList != null) {
                        adapter.addAll(newsList)
                    }
                }

                override fun onFailure(call: Call<Rss>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    companion object {
        const val BASE_URL = "https://www.mk.co.kr/"
    }
}