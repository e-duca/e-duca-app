package com.educa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Video : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_aula)

        val backToContent = findViewById<Button>(R.id.btn_back)

        backToContent.setOnClickListener {
            val contentPage = Intent(applicationContext, Content::class.java)
            startActivity(contentPage)
        }
    }
}