package dev.gaddal.auth.presentation.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.gaddal.core.presentation.designsystem.components.util.applyIf

@Composable
fun AuthStep(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    errorVisible: Boolean = false,
    errorMessage: String = "",
    useWeight: Boolean = true, // New parameter
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .applyIf(condition = useWeight) { Modifier.weight(1f) }
                .applyIf(condition = !useWeight) { Modifier.fillMaxWidth() }
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LogoAndTitleSection(
                title = title,
                subtitle = subtitle
            )
            content()
        }

        // Handle error messages
        ErrorMessageHandler(
            isVisible = errorVisible,
            errorMessage = errorMessage
        )
    }
}