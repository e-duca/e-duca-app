package com.educa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.educa.api.model.Answer

class AccessThread : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_thread)

        val btnBack = findViewById<Button>(R.id.btnBack)

        val array: String? = intent.getStringExtra("array")
        val current: String? = intent.getStringExtra("current")
        if (array != null) {
            Log.e("ARRAY ARRAY ARRAY", array)
        }

        btnBack.setOnClickListener {
            val myQuestions = Intent(applicationContext, MyQuestions::class.java)
            startActivity(myQuestions)
        }
    }
}