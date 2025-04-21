package dev.gaddal.auth.presentation.register.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.gaddal.auth.presentation.R
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.ui.LocalesPreview

@Composable
fun ErrorMessageBar(
    visible: Boolean,
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = visible) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(color = MaterialTheme.colorScheme.error),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.onError,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}


@LocalesPreview()
@Composable
fun ErrorMessageBarPreview() {
    SpendLessTheme {
        ErrorMessageBar(
            visible = true,
            errorMessage = stringResource(R.string.error_username_exists),
        )
    }
}