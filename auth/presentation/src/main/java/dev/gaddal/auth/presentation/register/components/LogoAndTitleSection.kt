package dev.gaddal.auth.presentation.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.gaddal.auth.presentation.R
import dev.gaddal.core.presentation.designsystem.AppIcons.WalletMoney
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.ui.LocalesPreview

@Composable
fun LogoAndTitleSection(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .padding(top = 32.dp)
            .size(64.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = WalletMoney,
            contentDescription = "Wallet money icon",
            modifier = Modifier.size(30.dp)
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = subtitle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }

    Spacer(modifier = Modifier.height(32.dp))
}

@LocalesPreview()
@Composable
fun LogoAndTitleSectionPreview() {
    SpendLessTheme {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoAndTitleSection(
                title = stringResource(R.string.create_pin),
                subtitle = stringResource(R.string.pin_login_description),
            )
        }
    }
}