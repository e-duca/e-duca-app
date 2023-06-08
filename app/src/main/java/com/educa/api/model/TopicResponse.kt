package com.educa.api.model

import com.google.gson.annotations.SerializedName

data class TopicResponseArray(
    @SerializedName("content")
    val topic: List<TopicResponse>
)

data class TopicResponse(
    val idTopico: Int,
    val titulo: String,
    val descricao: String,
    val dataCriacao: String,
    val usuario: User,
    val respostas: List<Answer>
)