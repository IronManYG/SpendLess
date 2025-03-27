package dev.gaddal.core.domain.auth

data class User(
    val id: Int,
    val name: String,
    val pinCode: String
)