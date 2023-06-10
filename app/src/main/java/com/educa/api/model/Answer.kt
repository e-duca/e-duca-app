package com.educa.api.model

data class Answer(
    val idResposta: Int,
    val resposta: String,
    val dataCriacao: String,
    val usuario: User
)
