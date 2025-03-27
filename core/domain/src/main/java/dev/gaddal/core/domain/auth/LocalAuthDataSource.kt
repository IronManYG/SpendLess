package dev.gaddal.core.domain.auth

import dev.gaddal.core.domain.util.DataError
import dev.gaddal.core.domain.util.Result

typealias UserId = Int

interface LocalAuthDataSource {
    suspend fun upsertUser(user: User): Result<UserId, DataError.Local>
    suspend fun getUserByUsername(username: String): Result<User, DataError.Local>
    suspend fun isUsernameAvailable(username: String): Boolean
}