package com.educa.api.model

data class Content(
    val titulo: String,
    val tempoEstimado: String,
    val habilidade : String, // TODO: Ajustar habilidade para aceitar habilidade.codigo
    val texto: String,
    val urlVideo: String
)
