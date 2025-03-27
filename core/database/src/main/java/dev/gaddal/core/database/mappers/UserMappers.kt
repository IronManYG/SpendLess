package dev.gaddal.core.database.mappers

import dev.gaddal.core.database.entity.UserEntity
import dev.gaddal.core.domain.auth.User

fun UserEntity.toUser(): User {
    return User(
        id = id,
        name = name,
        pinCode = pinCode
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        name = name,
        pinCode = pinCode
    )
}