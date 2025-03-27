package dev.gaddal.core.data.auth

import dev.gaddal.core.domain.AuthInfo

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(
        username = username,
        pinCode = pinCode
    )
}

fun AuthInfoSerializable.toAuthInfo(): AuthInfo {
    return AuthInfo(
        username = username,
        pinCode = pinCode
    )
}