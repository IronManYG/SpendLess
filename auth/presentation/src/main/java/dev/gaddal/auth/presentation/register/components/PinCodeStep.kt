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
fun PinCodeStep(
    modifier: Modifier = Modifier,
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    AuthStep(
        modifier = modifier,
        title = stringResource(R.string.create_pin),
        subtitle = stringResource(R.string.pin_login_description),
        errorVisible = false,
        errorMessage = ""
    ) {
        PinInputSection(
            pinLength = state.pinCode.length,
            onPinInput = { digit -> onAction(RegisterAction.OnPinInput(digit)) },
            onPinDelete = { onAction(RegisterAction.OnPinDelete) }
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