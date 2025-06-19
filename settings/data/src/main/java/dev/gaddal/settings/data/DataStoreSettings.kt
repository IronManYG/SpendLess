package dev.gaddal.settings.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.gaddal.settings.domain.Currency
import dev.gaddal.settings.domain.SettingsPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class DataStoreSettings(
    private val context: Context
) : SettingsPreferences {

    companion object {
        private val Context.settingsDataStore by preferencesDataStore(
            name = "settings_datastore"
        )

        // Define preference keys
        private val DEFAULT_EXPENSES_FORMAT = stringPreferencesKey("default_expenses_format")
        private val DEFAULT_CURRENCY_NAME = stringPreferencesKey("default_currency_name")
        private val DEFAULT_CURRENCY_SYMBOL = stringPreferencesKey("default_currency_symbol")
        private val DEFAULT_DECIMAL_SEPARATOR = stringPreferencesKey("default_decimal_separator")
        private val DEFAULT_THOUSANDS_SEPARATOR =
            stringPreferencesKey("default_thousands_separator")
    }

    override suspend fun saveDefaultExpensesFormat(format: String) {
        context.settingsDataStore.edit { preferences ->
            preferences[DEFAULT_EXPENSES_FORMAT] = format
        }
    }

    override fun observeDefaultExpensesFormat(): Flow<String> {
        return context.settingsDataStore.data
            .map { preferences ->
                preferences[DEFAULT_EXPENSES_FORMAT] ?: ""
            }
            .distinctUntilChanged()
    }

    override suspend fun saveDefaultCurrency(currency: Currency) {
        context.settingsDataStore.edit { preferences ->
            preferences[DEFAULT_CURRENCY_NAME] = currency.name
            preferences[DEFAULT_CURRENCY_SYMBOL] = currency.symbol
        }
    }

    override fun observeDefaultCurrency(): Flow<Currency> {
        return context.settingsDataStore.data
            .map { preferences ->
                val name = preferences[DEFAULT_CURRENCY_NAME] ?: "USD"
                val symbol = preferences[DEFAULT_CURRENCY_SYMBOL] ?: "$"
                Currency(name, symbol)
            }
            .distinctUntilChanged()
    }

    override suspend fun saveDefaultDecimalSeparator(separator: String) {
        context.settingsDataStore.edit { preferences ->
            preferences[DEFAULT_DECIMAL_SEPARATOR] = separator
        }
    }

    override fun observeDefaultDecimalSeparator(): Flow<String> {
        return context.settingsDataStore.data
            .map { preferences ->
                preferences[DEFAULT_DECIMAL_SEPARATOR] ?: "."
            }
            .distinctUntilChanged()
    }

    override suspend fun saveDefaultThousandsSeparator(separator: String) {
        context.settingsDataStore.edit { preferences ->
            preferences[DEFAULT_THOUSANDS_SEPARATOR] = separator
        }
    }

    override fun observeDefaultThousandsSeparator(): Flow<String> {
        return context.settingsDataStore.data
            .map { preferences ->
                preferences[DEFAULT_THOUSANDS_SEPARATOR] ?: ","
            }
            .distinctUntilChanged()
    }
}