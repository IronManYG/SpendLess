package dev.gaddal.settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.gaddal.core.presentation.designsystem.colors.AppColors.OnPrimaryFixed
import dev.gaddal.core.presentation.designsystem.colors.AppColors.PrimaryContainer_SL08
import dev.gaddal.core.presentation.designsystem.colors.AppColors.SurfaceContainerLowest

@Composable
fun PreferenceSelector(
    title: String,
    options: List<String>,
    selectedOptionIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    containerBackgroundColor: Color = PrimaryContainer_SL08,
    selectedOptionBackgroundColor: Color = SurfaceContainerLowest,
    unselectedOptionBackgroundColor: Color = Color.Transparent,
    selectedOptionContentColor: Color = MaterialTheme.colorScheme.primary,
    unselectedOptionContentColor: Color = OnPrimaryFixed
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Row(
            modifier = Modifier
                .background(containerBackgroundColor, RoundedCornerShape(16.dp))
                .height(48.dp)
                .padding(4.dp)
                .fillMaxWidth(),
        ) {
            options.forEachIndexed { index, option ->
                Button(
                    onClick = { onOptionSelected(index) },
                    modifier = Modifier
                        .height(48.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (index == selectedOptionIndex) selectedOptionBackgroundColor
                        else unselectedOptionBackgroundColor,
                        contentColor = if (index == selectedOptionIndex) selectedOptionContentColor
                        else unselectedOptionContentColor,
                    ),
                ) {
                    Text(
                        text = option,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
    }
}