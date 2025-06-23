package dev.gaddal.settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.designsystem.colors.AppColors.SurfaceContainerLowest
import dev.gaddal.core.presentation.designsystem.components.util.dropShadow
import dev.gaddal.settings.domain.Currency
import dev.gaddal.settings.presentation.R
import dev.gaddal.settings.presentation.SettingsAction
import dev.gaddal.settings.presentation.SettingsState

@Composable
fun Preferences(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {
        // Title & Subtitle
        Text(
            text = buildString {
                appendLine(stringResource(R.string.preferences_title_line1))
                append(stringResource(R.string.preferences_title_line2))
            },
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.preferences_subtitle),
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Example format
        Column(
            modifier = Modifier
                .dropShadow(
                    shape = RoundedCornerShape(16.dp),
                    blur = 20.dp,
                    offsetY = 6.dp,
                    spread = 4.dp,
                )
                .fillMaxWidth()
                .background(SurfaceContainerLowest, RoundedCornerShape(16.dp))
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = state.formattedExampleAmount,
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = stringResource(R.string.preferences_amount_description),
                style = MaterialTheme.typography.bodySmall,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Expenses format
        PreferenceSelector(
            title = stringResource(R.string.preferences_expenses_format),
            options = listOf(
                stringResource(R.string.preferences_expenses_format_minus).replace(
                    "$",
                    state.selectedCurrency?.symbol ?: "$"
                ),
                stringResource(R.string.preferences_expenses_format_parentheses).replace(
                    "$",
                    state.selectedCurrency?.symbol ?: "$"
                )
            ),
            selectedOptionIndex = state.selectedExpensesFormat,
            onOptionSelected = { onAction(SettingsAction.OnExpensesFormatSelected(it)) },
            selectedOptionContentColor = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(8.dp))

        CurrencyDropdown(
            currencies = state.currencies,
            selectedCurrency = state.selectedCurrency ?: state.currencies.firstOrNull()
            ?: Currency("USD", "$"),
            expanded = state.expandedCurrency,
            onExpandChange = { onAction(SettingsAction.OnCurrencyDropdownExpandToggle) },
            onCurrencySelected = { onAction(SettingsAction.OnCurrencySelected(it)) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Decimal separator
        PreferenceSelector(
            title = stringResource(R.string.preferences_decimal_separator),
            options = listOf(
                stringResource(R.string.preferences_decimal_separator_dot),
                stringResource(R.string.preferences_decimal_separator_comma)
            ),
            selectedOptionIndex = state.selectedDecimalSeparator,
            onOptionSelected = { onAction(SettingsAction.OnDecimalSeparatorSelected(it)) },
            selectedOptionContentColor = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Thousands separator
        PreferenceSelector(
            title = stringResource(R.string.preferences_thousands_separator),
            options = listOf(
                stringResource(R.string.preferences_thousands_separator_dot),
                stringResource(R.string.preferences_thousands_separator_comma),
                stringResource(R.string.preferences_thousands_separator_space)
            ),
            selectedOptionIndex = state.selectedThousandsSeparator,
            onOptionSelected = { onAction(SettingsAction.OnThousandsSeparatorSelected(it)) },
            selectedOptionContentColor = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Start Tracking button
        Button(
            onClick = {
                onAction(SettingsAction.OnStartTrackingClick)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = state.selectedDecimalSeparator != state.selectedThousandsSeparator,
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(R.string.preferences_start_tracking),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreferencesPreview() {
    SpendLessTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Preferences(
                state = SettingsState(
                    currencies = listOf(
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
                    ),
                    selectedCurrency = Currency("USD", "$"),
                    selectedExpensesFormat = 0,
                    selectedDecimalSeparator = 0,
                    selectedThousandsSeparator = 0
                ),
                onAction = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}