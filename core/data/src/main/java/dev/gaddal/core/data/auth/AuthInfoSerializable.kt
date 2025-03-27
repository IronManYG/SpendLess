package dev.gaddal.core.data.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerializable(
    val username: String,
    val pinCode: String
)
