package dev.gaddal.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gaddal.core.presentation.designsystem.components.ProgressBarState
import dev.gaddal.settings.domain.Currency
import dev.gaddal.settings.domain.SettingsPreferences
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsPreferences: SettingsPreferences,
) : ViewModel() {
    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(SettingsState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadInitialData()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SettingsState()
        )

    private val eventChannel = Channel<SettingsEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnExpensesFormatSelected -> {
                _state.update { it.copy(selectedExpensesFormat = action.index) }
                    .also { updateFormattedExampleAmount() }
            }

            is SettingsAction.OnDecimalSeparatorSelected -> {
                _state.update { it.copy(selectedDecimalSeparator = action.index) }
                    .also { updateFormattedExampleAmount() }

            }

            is SettingsAction.OnThousandsSeparatorSelected -> {
                _state.update { it.copy(selectedThousandsSeparator = action.index) }
                    .also { updateFormattedExampleAmount() }
            }

            is SettingsAction.OnCurrencySelected -> {
                _state.update {
                    it.copy(
                        selectedCurrency = action.currency,
                        expandedCurrency = false
                    )
                }.also { updateFormattedExampleAmount() }
            }

            is SettingsAction.OnCurrencyDropdownExpandToggle -> {
                _state.update { it.copy(expandedCurrency = !it.expandedCurrency) }
            }

            is SettingsAction.OnStartTrackingClick -> {
                saveSettings()
            }

            else -> Unit
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            // Load currencies list
            val currencies = getCurrencies()
            _state.update {
                it.copy(
                    currencies = currencies,
                    selectedCurrency = currencies.firstOrNull()
                )
            }

            // Observe user preferences from data store
            launch {
                settingsPreferences.observeDefaultExpensesFormat().collect { format ->
                    val formatIndex = if (format.isEmpty()) 0 else format.toIntOrNull() ?: 0
                    _state.update { it.copy(selectedExpensesFormat = formatIndex) }
                    updateFormattedExampleAmount()
                }
            }

            launch {
                settingsPreferences.observeDefaultCurrency().collect { currency ->
                    val matchingCurrency =
                        currencies.find { it.name == currency.name } ?: currencies.first()
                    _state.update { it.copy(selectedCurrency = matchingCurrency) }
                    updateFormattedExampleAmount()
                }
            }

            launch {
                settingsPreferences.observeDefaultDecimalSeparator().collect { separator ->
                    val separatorIndex = when (separator) {
                        "." -> 0
                        "," -> 1
                        else -> 0
                    }
                    _state.update { it.copy(selectedDecimalSeparator = separatorIndex) }
                    updateFormattedExampleAmount()
                }
            }

            launch {
                settingsPreferences.observeDefaultThousandsSeparator().collect { separator ->
                    val separatorIndex = when (separator) {
                        "." -> 0
                        "," -> 1
                        " " -> 2
                        else -> 1
                    }
                    _state.update { it.copy(selectedThousandsSeparator = separatorIndex) }
                    updateFormattedExampleAmount()
                }
            }
        }
    }

    private fun saveSettings() {
        viewModelScope.launch {
            _state.update { it.copy(progressBarState = ProgressBarState.Loading) }

            // Save settings to preferences
            val currentState = _state.value
            settingsPreferences.saveDefaultExpensesFormat(currentState.selectedExpensesFormat.toString())
            currentState.selectedCurrency?.let { settingsPreferences.saveDefaultCurrency(it) }

            val decimalSeparator = if (currentState.selectedDecimalSeparator == 0) "." else ","
            settingsPreferences.saveDefaultDecimalSeparator(decimalSeparator)

            val thousandsSeparator = when (currentState.selectedThousandsSeparator) {
                0 -> "."
                1 -> ","
                else -> " "
            }
            settingsPreferences.saveDefaultThousandsSeparator(thousandsSeparator)

            _state.update { it.copy(progressBarState = ProgressBarState.Idle) }
            eventChannel.send(SettingsEvent.SettingsSaved)
        }
    }

    private fun updateFormattedExampleAmount(amount: Double = -10382.45) {
        val currentState = _state.value
        val formattedAmount = formatCurrencyAmount(
            amount = amount,
            expensesFormat = currentState.selectedExpensesFormat,
            decimalSeparator = currentState.selectedDecimalSeparator,
            thousandsSeparator = currentState.selectedThousandsSeparator,
            currencySymbol = currentState.selectedCurrency?.symbol ?: "$"
        )

        _state.update { it.copy(formattedExampleAmount = formattedAmount) }
    }

    private fun getCurrencies(): List<Currency> {
        return listOf(
            Currency("USD Dollar (USD)", "$"),
            Currency("Euro (EUR)", "€"),
            Currency("British Pound Sterling (GBP)", "£"),
            Currency("Japanese Yen (JPY)", "¥"),
            Currency("Swiss Franc (CHF)", "CHF"),
            Currency("Canadian Dollar (CAD)", "CA$"),
            Currency("Australian Dollar (AUD)", "AU$"),
            Currency("Chinese Yuan (CNY)", "¥"),
            Currency("Indian Rupee (INR)", "₹"),
            Currency("Brazilian Real (BRL)", "R$"),
            Currency("South African Rand (ZAR)", "R"),
            Currency("Russian Ruble (RUB)", "₽"),
            Currency("Mexican Peso (MXN)", "MX$"),
            Currency("South Korean Won (KRW)", "₩"),
            Currency("Turkish Lira (TRY)", "₺"),
            Currency("Hong Kong Dollar (HKD)", "HK$"),
            Currency("Singapore Dollar (SGD)", "S$"),
            Currency("Swedish Krona (SEK)", "kr"),
            Currency("Norwegian Krone (NOK)", "kr"),
            Currency("Danish Krone (DKK)", "kr"),
            Currency("Polish Złoty (PLN)", "zł"),
            Currency("New Zealand Dollar (NZD)", "NZ$"),
            Currency("Thai Baht (THB)", "฿"),
            Currency("United Arab Emirates Dirham (AED)", "د.إ"),
            Currency("Saudi Riyal (SAR)", "ر.س"),
        )
    }

    /**
     * Formats currency amount according to user preferences
     */
    private fun formatCurrencyAmount(
        amount: Double,
        expensesFormat: Int,
        decimalSeparator: Int,
        thousandsSeparator: Int,
        currencySymbol: String
    ): String {
        val isNegative = amount < 0
        val absAmount = kotlin.math.abs(amount)

        // Parse amount into whole and decimal parts
        val wholePart = absAmount.toInt()
        val decimalPart = ((absAmount - wholePart) * 100).toInt()

        // Format decimal part based on selected separator
        val formattedDecimalPart = String.format(java.util.Locale.ENGLISH, "%02d", decimalPart)
        val decSeparator = if (decimalSeparator == 0) "." else ","

        // Format whole part with thousand separators
        val thousandSeparator = when (thousandsSeparator) {
            0 -> "."
            1 -> ","
            else -> "\u00A0" // For "1 000" format
        }

        // Apply thousand separators
        val formattedWholePart = wholePart.toString().reversed()
            .chunked(3)
            .joinToString(thousandSeparator)
            .reversed()

        // Combine whole and decimal parts
        val numberStr = "$formattedWholePart$decSeparator$formattedDecimalPart"

        // Apply expenses format and currency symbol
        return if (expensesFormat == 0) {
            // "-$10" format
            if (isNegative) "-$currencySymbol$numberStr" else "$currencySymbol$numberStr"
        } else {
            // "($10)" format
            if (isNegative) "($currencySymbol$numberStr)" else "$currencySymbol$numberStr"
        }
    }
}

