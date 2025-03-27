package dev.gaddal.auth.data.di

import dev.gaddal.auth.data.AuthRepositoryImpl
import dev.gaddal.auth.data.UsernamePatternValidator
import dev.gaddal.auth.domain.AuthRepository
import dev.gaddal.auth.domain.PatternValidator
import dev.gaddal.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        UsernamePatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}