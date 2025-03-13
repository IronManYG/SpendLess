package dev.gaddal.core.presentation.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.gaddal.core.presentation.designsystem.colors.ColorScheme

@Composable
fun SpendLessTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = FigtreeTypography,
        content = content
    )
}