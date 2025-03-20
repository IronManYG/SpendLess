package dev.gaddal.auth.presentation.register

sealed interface RegisterAction {
    data object OnNextClick: RegisterAction
    data object OnAlreadyHaveAnAccountClick: RegisterAction
}