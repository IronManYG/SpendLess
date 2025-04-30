package dev.gaddal.auth.presentation.register

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gaddal.auth.domain.AuthRepository
import dev.gaddal.auth.domain.UserDataValidator
import dev.gaddal.auth.presentation.register.components.RegistrationStep
import dev.gaddal.core.domain.util.Result
import dev.gaddal.core.presentation.designsystem.components.ProgressBarState
import dev.gaddal.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RegisterState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                viewModelScope.launch {
                    snapshotFlow { _state.value.username.text }
                        .collectLatest { username ->
                            // check format validation in real-time
                            val isValidUsername =
                                userDataValidator.isValidUsername(username.toString())
                            // Reset availability error when user types
                            _state.update {
                                it.copy(
                                    isUsernameValid = isValidUsername,
                                    isUsernameAvailable = true, // Reset on typing
                                    showError = true // Reset error visibility
                                )
                            }
                        }
                }
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RegisterState()
        )

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.OnNextClick -> {
                if (_state.value.currentStep == RegistrationStep.USERNAME
                    && _state.value.isUsernameValid
                ) {
                    viewModelScope.launch {
                        // Check availability only when clicking next
                        val isAvailable =
                            repository.isUsernameAvailable(_state.value.username.text.toString())
                        if (isAvailable) {
                            _state.update { it.copy(currentStep = RegistrationStep.PIN_CODE) }
                        } else {
                            _state.update { it.copy(isUsernameAvailable = false, showError = true) }
                        }
                    }
                }
            }

            RegisterAction.OnAlreadyHaveAnAccountClick -> {

            }

            RegisterAction.OnBackClick -> {
                if (_state.value.currentStep == RegistrationStep.PIN_CODE) {
                    // Move to USERNAME step
                    _state.update { it.copy(currentStep = RegistrationStep.USERNAME) }
                }

                if (_state.value.currentStep == RegistrationStep.CONFIRM_PIN) {
                    // Move to USERNAME step
                    _state.update { it.copy(currentStep = RegistrationStep.PIN_CODE) }
                }

                _state.update { it.copy(pinCode = "", confirmPinCode = "") }
            }

            is RegisterAction.OnPinInput -> {
                if (_state.value.pinCode.length < PIN_LENGTH) {
                    val updatedPinCode = _state.value.pinCode + action.digit
                    _state.update { it.copy(pinCode = updatedPinCode) }
                    if (updatedPinCode.length == PIN_LENGTH) {
                        // Automatically move to CONFIRM_PIN step
                        _state.update { it.copy(currentStep = RegistrationStep.CONFIRM_PIN) }
                    }
                }
            }

            RegisterAction.OnPinDelete -> {
                if (_state.value.pinCode.isNotEmpty()) {
                    _state.update { it.copy(pinCode = _state.value.pinCode.dropLast(1)) }
                }
            }

            is RegisterAction.OnConfirmPinInput -> {
                if (_state.value.confirmPinCode.length < PIN_LENGTH) {
                    val updatedConfirmPinCode = _state.value.confirmPinCode + action.digit
                    _state.update { it.copy(confirmPinCode = updatedConfirmPinCode) }
                    if (updatedConfirmPinCode.length == PIN_LENGTH) {
                        if (updatedConfirmPinCode == _state.value.pinCode) {
                            viewModelScope.launch {
                                _state.update { it.copy(progressBarState = ProgressBarState.Loading) }
                                val result = repository.register(
                                    username = _state.value.username.text.toString(),
                                    pinCode = _state.value.pinCode
                                )
                                _state.update { it.copy(progressBarState = ProgressBarState.Idle) }

                                when (result) {
                                    is Result.Error -> {
                                        eventChannel.send(RegisterEvent.Error(result.error.asUiText()))
                                    }

                                    is Result.Success -> {
                                        eventChannel.send(RegisterEvent.RegistrationSuccess)
                                    }
                                }

                            }
                        } else {
                            _state.update { it.copy(isPinsMatch = false, showError = true) }
                        }
                    }
                }
            }

            RegisterAction.OnConfirmPinDelete -> {
                if (_state.value.confirmPinCode.isNotEmpty()) {
                    _state.update {
                        it.copy(
                            confirmPinCode = _state.value.confirmPinCode.dropLast(1),
                            isPinsMatch = true
                        )
                    }
                }
            }

            RegisterAction.DismissError -> {
                _state.update { it.copy(showError = false) }
            }

            else -> Unit
        }
    }

    private companion object {
        const val PIN_LENGTH = 5
    }
}