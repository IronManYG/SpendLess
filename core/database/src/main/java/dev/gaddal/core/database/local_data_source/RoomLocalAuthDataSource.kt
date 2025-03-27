package dev.gaddal.core.database.local_data_source

import android.database.sqlite.SQLiteFullException
import dev.gaddal.core.database.dao.UserDao
import dev.gaddal.core.database.mappers.toUser
import dev.gaddal.core.database.mappers.toUserEntity
import dev.gaddal.core.domain.auth.LocalAuthDataSource
import dev.gaddal.core.domain.auth.User
import dev.gaddal.core.domain.auth.UserId
import dev.gaddal.core.domain.util.DataError
import dev.gaddal.core.domain.util.Result

class RoomLocalAuthDataSource(
    private val userDao: UserDao
) : LocalAuthDataSource {
    override suspend fun upsertUser(user: User): Result<UserId, DataError.Local> {
        return try {
            val entity = user.toUserEntity()
            val result = userDao.upsertUser(entity)
            Result.Success(result.toInt())
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun getUserByUsername(username: String): Result<User, DataError.Local> {
        return try {
            val result = userDao.getUserByUsername(username)
                ?: return Result.Error(DataError.Local.UNAUTHENTICATED)
            Result.Success(result.toUser())
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            // You could return a more generic local error or rethrow, etc.
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun isUsernameAvailable(username: String): Boolean {
        return userDao.isUsernameAvailable(username)
    }
}