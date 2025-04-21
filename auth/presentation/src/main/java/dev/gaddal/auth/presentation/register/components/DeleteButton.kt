package dev.gaddal.auth.presentation.register.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gaddal.core.presentation.designsystem.AppIcons.BackSpace
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.designsystem.colors.AppColors.OnPrimaryFixed
import dev.gaddal.core.presentation.designsystem.colors.AppColors.PrimaryFixed

@Composable
fun DeleteButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryFixed.copy(alpha = 0.3f),
            contentColor = OnPrimaryFixed
        ),
        modifier = modifier
    ) {
        Icon(
            imageVector = BackSpace,
            contentDescription = "Delete"
        )
    }
}

@Preview()
@Composable
fun DeleteButtonPreview() {
    SpendLessTheme {
        DeleteButton(
            onClick = {},
            modifier = Modifier
                .size(108.dp)
                .fillMaxSize()
        )
    }
}