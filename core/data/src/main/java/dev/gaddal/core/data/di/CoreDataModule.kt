package dev.gaddal.core.data.di

import dev.gaddal.core.data.auth.EncryptedSessionStorage
import dev.gaddal.core.domain.SessionStorage
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreDataModule = module {
    single<SessionStorage> {
        EncryptedSessionStorage(get(named("encrypted")))
    }
}