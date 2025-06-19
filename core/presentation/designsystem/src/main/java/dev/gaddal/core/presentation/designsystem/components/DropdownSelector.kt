package dev.gaddal.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import dev.gaddal.core.presentation.designsystem.colors.AppColors.SurfaceContainerLowest
import dev.gaddal.core.presentation.designsystem.components.util.dropShadow

/**
 * DropdownSelector
 *
 * A small wrapper that shows a label, then renders a button that looks like a dropdown
 * (so you can keep using your own menus).  Pass any Composable for the leading icon /
 * emoji chip; everything else is handled for you.
 */
@Composable
fun DropdownSelector(
    modifier: Modifier = Modifier,
    label: String,
    selectedText: String,
    selectedTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    onClick: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(
        start = 16.dp,
        end = 16.dp,
        top = 0.dp,
        bottom = 0.dp
    ),
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Open dropdown"
        )
    },
) {
    Column(modifier = modifier) {

        // ───── Label ──────────────────────────────────────────────────────────────
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelSmall
        )

        Spacer(Modifier.height(4.dp))

        // ───── Button “field” ────────────────────────────────────────────────────
        Button(
            onClick = onClick,
            modifier = Modifier
                .dropShadow(
                    shape = RoundedCornerShape(16.dp),
                    blur = 20.dp,
                    offsetY = 6.dp,
                    spread = 4.dp
                )
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SurfaceContainerLowest,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            contentPadding = contentPadding
        ) {
            // leading chip / symbol
            leadingContent?.let {
                it()
                Spacer(modifier = Modifier.width(8.dp))
            }

            // main text
            Text(
                text = selectedText,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = selectedTextStyle
            )

            trailingContent?.let {
                it()
            }
        }
    }
}
