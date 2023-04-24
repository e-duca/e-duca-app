package com.educa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.OnFocusChangeListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.educa.api.model.LoginRequest
import com.educa.api.model.LoginResponse
import com.educa.api.service.ApiClient
import com.educa.api.service.SessionManager
import com.google.android.material.textfield.TextInputLayout
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

        val loginButton = findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener {
            val emailField = findViewById<EditText>(R.id.ipt_email)
            val email = emailField.text.toString()

            val passwordField = findViewById<EditText>(R.id.ipt_password)
            val password = passwordField.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                login(email, password)
            } else {
                Toast.makeText(
                    baseContext,
                    "Os campos não podem estar vazios!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun login(email: String, password: String) {
        Log.w("ENTROU NA FUNÇÃO LOGIN", "  $email $password")
        apiClient.getAuthApiService().login(LoginRequest(email, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()?.token
                        if (loginResponse != null) {
                            sessionManager.saveAuthToken(loginResponse)
                            Toast.makeText(
                                baseContext, "Autenticação realizada com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.w("TOKEN", "${response.body()?.token}")

                            val contentPage = Intent(applicationContext, Content::class.java)
                            startActivity(contentPage)
                        }

                    } else {
                        Log.e(
                            "ERRO AO FAZER LOGIN",
                            "Call: ${call} Response: ${response}"
                        )
                        Toast.makeText(
                            baseContext,
                            "Erro ao fazer login, confirme seus dados e tente novamente!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(
                        baseContext,
                        "Erro no servidor! Por favor, tente novamente mais tarde. ERROR ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    t.printStackTrace()
                }
            })


    }
}
