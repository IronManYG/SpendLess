package dev.gaddal.auth.data

import dev.gaddal.auth.domain.AuthRepository
import dev.gaddal.core.domain.AuthInfo
import dev.gaddal.core.domain.SessionStorage
import dev.gaddal.core.domain.auth.LocalAuthDataSource
import dev.gaddal.core.domain.auth.User
import dev.gaddal.core.domain.util.DataError
import dev.gaddal.core.domain.util.EmptyResult
import dev.gaddal.core.domain.util.Result
import dev.gaddal.core.domain.util.asEmptyDataResult

class AuthRepositoryImpl(
    private val localAuthDataSource: LocalAuthDataSource,
    private val sessionStorage: SessionStorage
) : AuthRepository {

    override suspend fun isUsernameAvailable(username: String): Boolean {
        return localAuthDataSource.isUsernameAvailable(username)
    }

    override suspend fun login(
        username: String,
        pinCode: String
    ): EmptyResult<DataError.Local> {
        // 1) Retrieve the user without checking the PIN yet
        val userResult = localAuthDataSource.getUserByUsername(username)
        if (userResult is Result.Success) {
            val user = userResult.data
            // 2) Use Argon2Utils to verify
            val verified = SecureHashProvider.verifyPin(user.pinCode, pinCode)
            if (verified) {
                sessionStorage.set(AuthInfo(username = user.name, pinCode = user.pinCode))
                return Result.Success(Unit).asEmptyDataResult()
            }
            // If PIN verification fails, return an error
            return Result.Error(DataError.Local.INVALID_PIN).asEmptyDataResult()
        }
        // If user not found, return an error
        return Result.Error(DataError.Local.NOT_FOUND).asEmptyDataResult()
    }

    override suspend fun register(
        username: String,
        pinCode: String
    ): EmptyResult<DataError.Local> {
        // 1) Check if username is available
        if (!localAuthDataSource.isUsernameAvailable(username)) {
            return Result.Error(DataError.Local.ALREADY_EXISTS).asEmptyDataResult()
        }

        // 2) Hash the PIN before storing
        val hashedPin = SecureHashProvider.hashPin(pinCode)

        // 3) Insert new user (store the hash, not the raw PIN)
        val newUser = User(
            id = 0,
            name = username,
            pinCode = hashedPin
        )
        val result = localAuthDataSource.upsertUser(newUser)

        // 4) Return success
        return result.asEmptyDataResult()
    }
}