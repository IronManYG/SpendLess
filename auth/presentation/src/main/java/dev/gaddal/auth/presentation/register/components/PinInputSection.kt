package dev.gaddal.auth.presentation.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gaddal.core.presentation.designsystem.SpendLessTheme

@Composable
fun PinInputSection(
    pinLength: Int,
    onPinInput: (String) -> Unit,
    onPinDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PinDotsDisplay(
            pinLength = pinLength,
            maxLength = 5,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        NumericKeypad(
            onNumberClick = onPinInput,
            onDeleteClick = onPinDelete,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PinInputSectionPreview() {
    SpendLessTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .size(width = 332.dp, height = 544.dp)
        ) {
            PinInputSection(
                pinLength = 3,
                onPinInput = {},
                onPinDelete = {}
            )
        }
    }
}