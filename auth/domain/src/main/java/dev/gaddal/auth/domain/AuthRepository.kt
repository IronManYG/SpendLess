package dev.gaddal.auth.domain

import dev.gaddal.core.domain.util.DataError
import dev.gaddal.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun isUsernameAvailable(username: String): Boolean
    suspend fun login(username: String, pinCode: String): EmptyResult<DataError.Local>
    suspend fun register(username: String, pinCode: String): EmptyResult<DataError.Local>
}