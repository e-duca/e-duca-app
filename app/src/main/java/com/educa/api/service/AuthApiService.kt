package com.educa.api.service

import com.educa.api.model.LoginResponse
import com.educa.api.model.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface AuthApiService {

    //@GET("auth/usuario-secao")
    //fun getUser(@Header)

    @POST("/auth")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}