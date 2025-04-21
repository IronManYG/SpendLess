@file:OptIn(ExperimentalMaterial3Api::class)

package dev.gaddal.auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.gaddal.auth.presentation.R
import dev.gaddal.auth.presentation.register.components.ConfirmPinStep
import dev.gaddal.auth.presentation.register.components.PinCodeStep
import dev.gaddal.auth.presentation.register.components.RegistrationStep
import dev.gaddal.auth.presentation.register.components.UsernameStep
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.designsystem.components.SpendLessScaffold
import dev.gaddal.core.presentation.designsystem.components.SpendLessToolbar
import dev.gaddal.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterRoot(
    onSuccessfulRegistration: () -> Unit,
    onAlreadyHaveAnAccountClick: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegisterEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }

            RegisterEvent.RegistrationSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.registration_successful,
                    Toast.LENGTH_LONG
                ).show()
                onSuccessfulRegistration()
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterScreen(
        state = state,
        onAction = { action ->
            when (action) {
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
    SpendLessScaffold(
        progressBarState = state.progressBarState
    ) { innerPadding ->
        if (state.currentStep != RegistrationStep.USERNAME) {
            SpendLessToolbar(
                modifier = Modifier.fillMaxWidth(),
                showBackButton = true,
                showDownloadButton = false,
                showSettingsButton = false,
                onBackClick = {
                    onAction(RegisterAction.OnBackClick)
                },
            )
        }
        Box(contentAlignment = Alignment.Center) {
            when (state.currentStep) {
                RegistrationStep.USERNAME -> {
                    UsernameStep(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onAction = onAction
                    )
                }

                RegistrationStep.PIN_CODE -> {
                    // Pin code step
                    PinCodeStep(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onAction = onAction
                    )
                }

                RegistrationStep.CONFIRM_PIN -> {
                    ConfirmPinStep(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onAction = onAction
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SpendLessTheme {
        RegisterScreen(
            state = RegisterState(
                isUsernameValid = true,
                isUsernameAvailable = false
            ),
            onAction = {}
        )
    }
}