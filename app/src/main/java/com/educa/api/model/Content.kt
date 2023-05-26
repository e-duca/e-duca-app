package com.educa.api.model

data class Content(
    val titulo: String,
    val tempoEstimado: String,
    val habilidade : Habilidade,
    val texto: String,
    val urlVideo: String
)

data class Habilidade(
    val codigo: String
)
