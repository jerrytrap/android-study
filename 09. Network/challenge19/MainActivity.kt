package com.sample.doitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var textField: EditText
    private lateinit var requestButton: Button
    private lateinit var webData: TextView
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setView()
        setRequestButton()
    }

    private fun setView() {
        textField = findViewById(R.id.editText)
        requestButton = findViewById(R.id.button_request)
        webData = findViewById(R.id.textView_webData)
        webView = findViewById(R.id.webView)

        webView.settings.javaScriptEnabled = true
    }

    private fun setRequestButton() {
        requestButton.setOnClickListener {
            val url = textField.text.toString()
            requestWebData(url)
            webView.loadUrl(url)
        }
    }

    private fun requestWebData(url: String) {
        val requestQueue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                webData.text = response
            },
            { error ->
                error.printStackTrace()
            }
        )

        requestQueue.add(request)
    }
}