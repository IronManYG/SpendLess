package dev.gaddal.auth.presentation.register.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.gaddal.auth.presentation.R
import dev.gaddal.auth.presentation.register.RegisterAction
import dev.gaddal.auth.presentation.register.RegisterState
import dev.gaddal.core.presentation.designsystem.SpendLessTheme

@Composable
fun ConfirmPinStep(
    modifier: Modifier = Modifier,
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    AuthStep(
        modifier = modifier,
        title = stringResource(R.string.confirm_pin),
        subtitle = stringResource(R.string.repeat_pin_description),
        errorVisible = !state.isPinsMatch && state.showError,
        errorMessage = stringResource(R.string.error_pins_not_match),
        onErrorDismissed = { onAction(RegisterAction.DismissError) }
    ) {
        PinInputSection(
            pinLength = state.confirmPinCode.length,
            onPinInput = { digit -> onAction(RegisterAction.OnConfirmPinInput(digit)) },
            onPinDelete = { onAction(RegisterAction.OnConfirmPinDelete) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PinCodeStepPreview() {
    SpendLessTheme {
        PinCodeStep(
            state = RegisterState(
                pinCode = "123" // Sample pin code with 3 digits
            ),
            onAction = {}
        )
    }
}