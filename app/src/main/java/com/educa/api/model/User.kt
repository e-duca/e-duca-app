package com.educa.api.model

data class User(
    val idUsuario: Int,
    val nome: String,
    val sobrenome: String,
    val dataNasc: String,
    val email: String,
    val senha: String,
    val areaAtuacao: String,
    val inicioAtuacao: String,
    val perfis: List<Profile>,
)