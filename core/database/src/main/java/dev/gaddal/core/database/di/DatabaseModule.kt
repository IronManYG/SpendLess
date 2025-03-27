package dev.gaddal.core.database.di

import androidx.room.Room
import dev.gaddal.core.database.TransactionsDatabase
import dev.gaddal.core.database.local_data_source.RoomLocalAuthDataSource
import dev.gaddal.core.domain.auth.LocalAuthDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            TransactionsDatabase::class.java,
            "transactions.db"
        ).build()
    }
    single { get<TransactionsDatabase>().userDao }

    singleOf(::RoomLocalAuthDataSource).bind<LocalAuthDataSource>()
}