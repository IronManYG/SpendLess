package dev.gaddal.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.gaddal.core.database.dao.UserDao
import dev.gaddal.core.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1
)
abstract class TransactionsDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}