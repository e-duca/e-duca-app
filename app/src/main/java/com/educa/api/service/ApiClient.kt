package com.educa.api.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    var BASE_URL = "http://localhost:80/"
    //var BASE_URL = "https://educaback.hopto.org/"

    fun getAuthApiService() : AuthApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return  retrofit.create(AuthApiService::class.java)
    }

    fun getMainApiService() : MainApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return  retrofit.create(MainApiService::class.java)
    }


}