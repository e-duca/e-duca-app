package com.educa.api.model

data class Rating(
    val idAvaliacao: Int,
    val avaliacao: String,
    val usuario: User,
)
