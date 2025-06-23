package dev.gaddal.settings.presentation

import dev.gaddal.settings.domain.Currency

sealed interface SettingsAction {
    data class OnExpensesFormatSelected(val index: Int) : SettingsAction
    data object OnCurrencyDropdownExpandToggle : SettingsAction
    data class OnCurrencySelected(val currency: Currency) : SettingsAction
    data class OnDecimalSeparatorSelected(val index: Int) : SettingsAction
    data class OnThousandsSeparatorSelected(val index: Int) : SettingsAction
    data object OnStartTrackingClick : SettingsAction
}