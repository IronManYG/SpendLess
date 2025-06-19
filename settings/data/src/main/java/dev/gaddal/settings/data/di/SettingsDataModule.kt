package dev.gaddal.settings.data.di

import dev.gaddal.settings.data.DataStoreSettings
import dev.gaddal.settings.domain.SettingsPreferences
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsDataModule = module {
    singleOf(::DataStoreSettings) bind SettingsPreferences::class
}