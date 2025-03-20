package dev.gaddal.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.gaddal.auth.presentation.register.components.ActionButtonsSection
import dev.gaddal.auth.presentation.register.components.LogoAndTitleSection
import dev.gaddal.auth.presentation.register.components.UsernameInputSection
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.designsystem.components.SpendLessScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterRoot(
    onNextClick: () -> Unit,
    onAlreadyHaveAnAccountClick: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is RegisterAction.OnNextClick -> onNextClick()
                is RegisterAction.OnAlreadyHaveAnAccountClick -> onAlreadyHaveAnAccountClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
) {
    SpendLessScaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LogoAndTitleSection()
            UsernameInputSection(state)
            ActionButtonsSection(state, onAction)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SpendLessTheme {
        RegisterScreen(
            state = RegisterState(
                isUsernameValid = true
            ),
            onAction = {}
        )
    }
}