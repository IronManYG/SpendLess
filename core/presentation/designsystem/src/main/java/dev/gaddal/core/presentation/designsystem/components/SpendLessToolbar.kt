@file:OptIn(ExperimentalMaterial3Api::class)

package dev.gaddal.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gaddal.core.presentation.designsystem.AppIcons.ArrowBack
import dev.gaddal.core.presentation.designsystem.AppIcons.Download
import dev.gaddal.core.presentation.designsystem.AppIcons.Settings
import dev.gaddal.core.presentation.designsystem.R
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.designsystem.colors.AppColors.OnPrimary
import dev.gaddal.core.presentation.designsystem.colors.AppColors.OnPrimaryFixed

@Composable
fun SpendLessToolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    showBackButton: Boolean,
    showDownloadButton: Boolean,
    showSettingsButton: Boolean,
    onBackClick: () -> Unit = {},
    onDownloadClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                startContent?.invoke()
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true,
                    maxLines = 1,
                )
            }
        },
        modifier = modifier,
        navigationIcon = {
            if (showBackButton) {
                IconButton(
                    onClick = onBackClick,
                ) {
                    Icon(
                        imageVector = ArrowBack,
                        contentDescription = stringResource(id = R.string.go_back),
                    )
                }
            }
        },
        actions = {
            if (showDownloadButton) {
                Button(
                    onClick = onDownloadClick,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OnPrimary.copy(alpha = 0.12f),
                        contentColor = OnPrimary
                    ),
                    modifier = Modifier.size(48.dp),
                    contentPadding = contentPadding(
                        start = 0.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    )
                ) {
                    Icon(
                        imageVector = Download,
                        contentDescription = stringResource(id = R.string.download),
                        modifier = Modifier.size(24.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            if (showSettingsButton) {
                Button(
                    onClick = onSettingsClick,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OnPrimary.copy(alpha = 0.12f),
                        contentColor = OnPrimary
                    ),
                    modifier = Modifier.size(48.dp),
                    contentPadding = contentPadding(
                        start = 0.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    )
                ) {
                    Icon(
                        imageVector = Settings,
                        contentDescription = stringResource(id = R.string.settings),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        scrollBehavior = scrollBehavior,
    )
}

@Preview
@Composable
fun SpendLessToolbarPreview() {
    SpendLessTheme {
        Surface(
            color = OnPrimaryFixed,
        ) {
            SpendLessToolbar(
                title = "",
                modifier = Modifier.fillMaxWidth(),
                showBackButton = true,
                showDownloadButton = true,
                showSettingsButton = true,
                startContent = {},
            )
        }
    }
}