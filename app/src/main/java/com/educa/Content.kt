package com.educa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Content : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val card01 = findViewById<androidx.fragment.app.FragmentContainerView>(R.id.fg_card01)
        card01.setOnClickListener {
            val readingPage = Intent(applicationContext, Reading::class.java)
            startActivity(readingPage)
        }

        val card02 = findViewById<androidx.fragment.app.FragmentContainerView>(R.id.fg_card02)
        card02.setOnClickListener {
            val videoPage = Intent(applicationContext, Video::class.java)
            startActivity(videoPage)
        }
    }
}