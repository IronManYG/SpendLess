package dev.gaddal.auth.presentation.register.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.gaddal.auth.presentation.R
import dev.gaddal.auth.presentation.register.RegisterAction
import dev.gaddal.auth.presentation.register.RegisterState
import dev.gaddal.core.presentation.designsystem.SpendLessTheme

@Composable
fun UsernameStep(
    modifier: Modifier = Modifier,
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    val scrollState = rememberScrollState()
    var contentHeight by remember { mutableIntStateOf(0) }

    // Scroll dynamically based on content height
    LaunchedEffect(contentHeight) {
        scrollState.animateScrollTo(contentHeight)
    }

    AuthStep(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .imePadding()
            .onGloballyPositioned { layoutCoordinates ->
                contentHeight = layoutCoordinates.size.height
            },
        title = buildString {
            appendLine(stringResource(R.string.welcome_message))
            append(stringResource(R.string.welcome_question))
        },
        subtitle = stringResource(R.string.username_creation_label),
        errorVisible = !state.isUsernameAvailable,
        errorMessage = stringResource(R.string.error_username_exists),
        useWeight = false
    ) {
        UsernameInputSection(state)
        ActionButtonsSection(state, onAction)
    }
}

@Preview(showBackground = true)
@Composable
private fun UsernameStepPreview() {
    SpendLessTheme {
        UsernameStep(
            state = RegisterState(
                pinCode = "123" // Sample pin code with 3 digits
            ),
            onAction = {}
        )
    }
}