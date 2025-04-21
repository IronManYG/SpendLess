package dev.gaddal.auth.presentation.register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessageHandler(
    isVisible: Boolean,
    errorMessage: String
) {
    if (isVisible) {
        ErrorMessageBar(
            visible = true,
            errorMessage = errorMessage
        )
    } else {
        Box(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .background(color = Color.Transparent),
            contentAlignment = Alignment.Center
        ) {}
    }
}