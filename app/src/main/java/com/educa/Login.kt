package com.educa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.educa.api.service.SessionManager
import com.educa.api.model.LoginRequest
import com.educa.api.model.LoginResponse
import com.educa.api.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        val emailField = findViewById<EditText>(R.id.ipt_email)
        val email = emailField.text.toString()

        val passwordField = findViewById<EditText>(R.id.ipt_password)
        val password = passwordField.text.toString()

        val loginButton = findViewById<Button>(R.id.btn_cadastro)

        loginButton.setOnClickListener {
            apiClient.getAuthApiService().login(LoginRequest(email, password))
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val loginResponse = response.body()

                        if (loginResponse?.statusCode == 200) {
                            sessionManager.saveAuthToken(loginResponse.token)
                        } else {
                            // Error logging in
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(
                            baseContext, "Erro na API: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        t.printStackTrace()
                    }
                })
        }
    }
}
