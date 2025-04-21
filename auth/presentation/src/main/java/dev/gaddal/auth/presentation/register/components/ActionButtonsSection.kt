package dev.gaddal.auth.presentation.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.gaddal.auth.presentation.R
import dev.gaddal.auth.presentation.register.RegisterAction
import dev.gaddal.auth.presentation.register.RegisterState
import dev.gaddal.core.presentation.designsystem.AppIcons.ArrowForward
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.ui.LocalesPreview

@Composable
fun ActionButtonsSection(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    Button(
        onClick = { onAction(RegisterAction.OnNextClick) },
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        enabled = state.isUsernameValid && state.isUsernameAvailable,
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.next_button_label),
                color = when {
                    state.isUsernameValid && state.isUsernameAvailable -> MaterialTheme.colorScheme.onPrimary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = when {
                    state.isUsernameValid && state.isUsernameAvailable -> MaterialTheme.colorScheme.onPrimary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    TextButton(
        onClick = { onAction(RegisterAction.OnAlreadyHaveAnAccountClick) }
    ) {
        Text(
            text = stringResource(R.string.login_prompt),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@LocalesPreview
@Composable
fun ActionButtonsSectionPreview() {
    SpendLessTheme {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ActionButtonsSection(
                state = RegisterState(
                    isUsernameValid = true,
                    isUsernameAvailable = true
                ),
                onAction = {}
            )
        }
    }
}