package com.sample.doitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val baseUrl = "https://jsonplaceholder.typicode.com"
        val url = "https://jsonplaceholder.typicode.com/posts/1"
        Thread { requestByHttpURLConnection(url) }.start()
        requestByVolley(url)
        requestByRetrofit(baseUrl)
    }

    private fun requestByHttpURLConnection(requestUrl: String) {
        try {
            val url = URL(requestUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            val responseCode = connection.responseCode
            if (responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))

                while (true) {
                    val line = reader.readLine() ?: break
                    println(line)
                }

                reader.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun requestByVolley(requestUrl: String) {
        val requestQueue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET,
            requestUrl,
            { response ->
                println("response: $response")
                printJsonResponse(response)
            },
            { error ->
                println("error: ${error.message}")
            }
        )

        requestQueue.add(request)
    }

    private fun requestByRetrofit(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val networkService = retrofit.create(NetworkService::class.java)
        val call = networkService.getData(id = 1)

        call.enqueue(object : Callback<SampleData> {
            override fun onResponse(call: Call<SampleData>, response: Response<SampleData>) {
                val data = response.body()
                println(data)
            }

            override fun onFailure(call: Call<SampleData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun printJsonResponse(response: String) {
        try {
            val json = JSONObject(response)
            val userId = json.getInt("userId")
            val id = json.getInt("id")
            val title = json.getString("title")
            val body = json.getString("body")

            println("userId: $userId, id: $id, title: $title, body: $body")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}