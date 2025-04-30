package dev.gaddal.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import dev.gaddal.auth.presentation.register.components.RegistrationStep
import dev.gaddal.core.presentation.designsystem.components.ProgressBarState

data class RegisterState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val username: TextFieldState = TextFieldState(),
    val isUsernameValid: Boolean = false,
    val isUsernameAvailable: Boolean = true,
    val currentStep: RegistrationStep = RegistrationStep.USERNAME,
    val pinCode: String = "",
    val confirmPinCode: String = "",
    val isPinsMatch: Boolean = true,
    val showError: Boolean = true,
)