package dev.gaddal.auth.presentation.register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.gaddal.auth.presentation.R
import dev.gaddal.auth.presentation.register.RegisterState
import dev.gaddal.core.presentation.designsystem.FigtreeFontFamily
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.designsystem.colors.AppColors.OnBackground_SL08

@Composable
fun UsernameInputSection(state: RegisterState) {
    var isFocused by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = OnBackground_SL08,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            state = state.username,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FigtreeFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp,
                lineHeight = 44.sp,
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            decorator = { innerBox ->
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    if (state.username.text.isEmpty() && !isFocused) {
                        Text(
                            text = stringResource(R.string.username_hint), // hint
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                alpha = 0.4f
                            ),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.displayMedium,
                        )
                    }
                    innerBox()
                }
            }
        )

    }

    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(showBackground = true)
@Composable
private fun UsernameInputSectionPreview() {
    SpendLessTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            UsernameInputSection(
                state = RegisterState(),
            )
        }
    }
}