package com.educa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.webkit.WebView
import android.webkit.WebViewClient


class Video : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_aula)

        val backToContent = findViewById<Button>(R.id.btn_back)

        backToContent.setOnClickListener {
            val contentPage = Intent(applicationContext, Content::class.java)
            startActivity(contentPage)
        }

        val webView: WebView = findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true

        val videoId = "j6dy4VrsFvA" // Substitua VIDEO_ID pelo ID do vídeo do YouTube que você deseja exibir
        val url = "https://www.youtube.com/embed/$videoId"

        webView.loadUrl(url)
    }
}