package com.educa.api.service

import com.educa.api.model.ContentResponseArray
import com.educa.api.model.Student
import com.educa.api.model.Topic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface MainApiService {

    @GET("api/conteudos")
    fun getAllContent() : Call<ContentResponseArray>

    @POST("api/usuarios/estudantes/")
    fun registerStudent(@Body student: Student) : Call<Student>

    @GET("api/topicos/usuario-secao")
    fun getTopic() : Call<List<Topic>>

    @GET("api/topicos")
    fun getAllTopics() : Call<List<Topic>>

    @POST("api/topicos")
    fun registerTopic(@Body topic: Topic) : Call<Topic>

    @PATCH("api/topicos/{id}")
    fun updateTopic(@Path("id") @Body topic: Topic): Call<Topic>

    @DELETE("api/topicos/{id}")
    fun deleteTopic(@Path("id") topicId: Int) : Callback<Topic>

    //@POST("api/topicos/respostas")
    //fun registerAnswer()

    //@PUT("api/topicos/respostas/{id}")
    //fun updateAnswer(@Path("id"))

    //@DELETE("api/topicos/respostas/{id}")
    //fun deleteAnswer(@Path("id"))

}