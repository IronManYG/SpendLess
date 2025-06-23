package dev.gaddal.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.designsystem.components.DropdownSelector
import dev.gaddal.settings.domain.Currency
import dev.gaddal.settings.presentation.R

@Composable
fun CurrencyDropdown(
    currencies: List<Currency>,
    selectedCurrency: Currency,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onCurrencySelected: (Currency) -> Unit,
    modifier: Modifier = Modifier
) {
    var columnSize = remember { mutableStateOf(Size.Zero) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                columnSize.value = coordinates.size.toSize()
            }
    ) {
        // Currency Dropdown
        DropdownSelector(
            label = stringResource(R.string.currency),
            selectedText = selectedCurrency.name,
            onClick = { onExpandChange(true) },
            leadingContent = {
                Text(
                    text = selectedCurrency.symbol,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium
                )
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        CurrenciesDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandChange(false) },
            allCurrencies = currencies,
            selectedCurrency = selectedCurrency,
            onCurrencyClick = { currency ->
                onCurrencySelected(currency)
                onExpandChange(false)
            },
            modifier = Modifier
                .width(
                    with(LocalDensity.current) {
                        columnSize.value.width.toDp()
                    }
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyPreview() {
    SpendLessTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            CurrencyDropdown(
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
                expanded = false,
                onExpandChange = {},
                onCurrencySelected = { currency -> },
            )
        }
    }
}
