package dev.gaddal.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.gaddal.core.database.entity.UserEntity

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(userEntity: UserEntity): Long

    @Query("SELECT * FROM users WHERE name = :userName LIMIT 1")
    suspend fun getUserByUsername(userName: String): UserEntity?

    @Query("SELECT CASE WHEN NOT EXISTS(SELECT 1 FROM users WHERE name = :userName) THEN 1 ELSE 0 END")
    suspend fun isUsernameAvailable(userName: String): Boolean
}