package dev.gaddal.settings.presentation

import dev.gaddal.core.presentation.designsystem.components.ProgressBarState
import dev.gaddal.settings.domain.Currency

data class SettingsState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val currencies: List<Currency> = emptyList(),

    // Preferences
    val formattedExampleAmount: String = "",
    val selectedCurrency: Currency? = null,
    val expandedCurrency: Boolean = false,
    val selectedExpensesFormat: Int = 0,
    val selectedDecimalSeparator: Int = 0,
    val selectedThousandsSeparator: Int = 0,
)