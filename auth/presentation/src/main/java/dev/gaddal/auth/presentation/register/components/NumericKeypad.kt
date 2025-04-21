package dev.gaddal.auth.presentation.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gaddal.core.presentation.designsystem.SpendLessTheme

@Composable
fun NumericKeypad(
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keypadSize = 108.dp
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
    ) {
        val rowsModifier = Modifier
            .weight(1f)

        val keypadsModifier = Modifier
            .weight(1f)
            .sizeIn(maxWidth = keypadSize, maxHeight = keypadSize)
            .fillMaxSize()

        // First row: 1, 2, 3
        Row(
            modifier = rowsModifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (number in 1..3) {
                NumberButton(
                    number = number.toString(),
                    onClick = { onNumberClick(number.toString()) },
                    modifier = keypadsModifier
                )
            }
        }

        // Second row: 4, 5, 6
        Row(
            modifier = rowsModifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (number in 4..6) {
                NumberButton(
                    number = number.toString(),
                    onClick = { onNumberClick(number.toString()) },
                    modifier = keypadsModifier
                )
            }
        }

        // Third row: 7, 8, 9
        Row(
            modifier = rowsModifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (number in 7..9) {
                NumberButton(
                    number = number.toString(),
                    onClick = { onNumberClick(number.toString()) },
                    modifier = keypadsModifier
                )
            }
        }

        // Fourth row: empty, 0, delete
        Row(
            modifier = rowsModifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Empty cell
            Box(
                modifier = keypadsModifier,
                contentAlignment = Alignment.Center
            ) {
                // Could be an empty Box or something else
            }

            // "0" button
            NumberButton(
                number = "0",
                onClick = { onNumberClick("0") },
                modifier = keypadsModifier
            )

            // Delete button
            DeleteButton(
                onClick = onDeleteClick,
                modifier = keypadsModifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumericKeypadPreview() {
    SpendLessTheme {
        NumericKeypad(
            onNumberClick = {},
            onDeleteClick = {},
            modifier = Modifier.size(width = 332.dp, height = 444.dp)
        )
    }
}