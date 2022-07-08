package com.rajat.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class NewsWebview : AppCompatActivity() {

    private lateinit var webView:WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_webview)

        var url : String? = intent.getStringExtra("url")

        webView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url!!)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}