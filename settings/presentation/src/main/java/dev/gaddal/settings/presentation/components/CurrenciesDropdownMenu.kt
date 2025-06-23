package dev.gaddal.settings.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import dev.gaddal.core.presentation.designsystem.colors.AppColors.OnSurface_SL12
import dev.gaddal.core.presentation.designsystem.colors.AppColors.SurfaceContainerLowest
import dev.gaddal.core.presentation.designsystem.components.util.verticalColumnScrollbar
import dev.gaddal.settings.domain.Currency

@Composable
fun CurrenciesDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: PopupProperties = PopupProperties(),
    allCurrencies: List<Currency>,
    selectedCurrency: Currency,
    onCurrencyClick: (Currency) -> Unit,
) {
    val scrollState = rememberScrollState()

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
            .verticalColumnScrollbar(
                scrollState = scrollState,
                showScrollBarTrack = false,
                scrollBarColor = OnSurface_SL12,
                endPadding = 12f,
                topPadding = 16.dp,     // Space at the top
                bottomPadding = 16.dp,  // Space at the bottom
                maxHeight = 100.dp,
                alwaysShow = false,
            )
            .fillMaxWidth()
            .heightIn(max = 240.dp),
        scrollState = scrollState,
        properties = properties,
        shape = RoundedCornerShape(16.dp),
        containerColor = SurfaceContainerLowest,
    ) {
        allCurrencies.forEach { currency ->
            val isSelected = currency == selectedCurrency
            CurrencyDropdownMenuItem(
                currency = currency,
                isSelected = isSelected,
                onCurrencySelected = {
                    onCurrencyClick(currency)
                },
            )
        }
    }
}

