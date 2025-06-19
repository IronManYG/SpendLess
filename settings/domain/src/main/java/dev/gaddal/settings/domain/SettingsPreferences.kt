package dev.gaddal.settings.domain

import kotlinx.coroutines.flow.Flow

interface SettingsPreferences {
    // Preferences
    suspend fun saveDefaultExpensesFormat(format: String)
    fun observeDefaultExpensesFormat(): Flow<String>

    suspend fun saveDefaultCurrency(currency: Currency)
    fun observeDefaultCurrency(): Flow<Currency>

    suspend fun saveDefaultDecimalSeparator(separator: String)
    fun observeDefaultDecimalSeparator(): Flow<String>

    suspend fun saveDefaultThousandsSeparator(separator: String)
    fun observeDefaultThousandsSeparator(): Flow<String>

    // Security
}