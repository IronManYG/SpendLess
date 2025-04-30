package dev.gaddal.auth.presentation.register

sealed interface RegisterAction {
    data object OnNextClick: RegisterAction
    data object OnAlreadyHaveAnAccountClick: RegisterAction
    data object OnBackClick : RegisterAction
    data class OnPinInput(val digit: String) : RegisterAction
    data object OnPinDelete : RegisterAction
    data class OnConfirmPinInput(val digit: String) : RegisterAction
    data object OnConfirmPinDelete : RegisterAction
    data object DismissError : RegisterAction
}