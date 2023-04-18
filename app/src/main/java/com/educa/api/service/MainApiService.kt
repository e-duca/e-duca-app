package com.educa.api.service

import com.educa.api.model.Content
import com.educa.api.model.Student
import retrofit2.Call
import retrofit2.http.*

interface MainApiService {

    @GET("api/conteudos")
    fun getAllContent() : Call<List<Content>>

    @POST("api/usuarios/estudantes/")
    fun registerStudent(@Body student: Student) : Call<Student>

    //@GET("api/topicos/usuario-secao")
    //fun getTopic() : Call<List<Topic>>

    //@GET("api/topicos")
    //fun getAllTopics() : Call<List<Topic>>

    //@POST("api/topicos")
    //fun registerTopic()

    //@PUT("api/topicos/{id}")
    //fun updateTopic(@Path("id"))

    //@DELETE("api/topicos/{id}")
    //fun deleteTopic(@Path("id"))

    //@POST("api/topicos/respostas")
    //fun registerAnswer()

    //@PUT("api/topicos/respostas/{id}")
    //fun updateAnswer(@Path("id"))

    //@DELETE("api/topicos/respostas/{id}")
    //fun deleteAnswer(@Path("id"))

}