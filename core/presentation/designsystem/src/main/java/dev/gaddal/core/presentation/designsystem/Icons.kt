package dev.gaddal.core.presentation.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object AppIcons {
    val WalletMoney: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.wallet_money)

    val ArrowBack: ImageVector
        @Composable
        get() = Icons.AutoMirrored.Default.ArrowBack

    val ArrowForward: ImageVector
        @Composable
        get() = Icons.AutoMirrored.Default.ArrowForward

    val Check: ImageVector
        @Composable
        get() = Icons.Default.Check

    val Close: ImageVector
        @Composable
        get() = Icons.Default.Close

    val Add: ImageVector
        @Composable
        get() = Icons.Default.Add

    val ArrowDropUp: ImageVector
        @Composable
        get() = Icons.Default.ArrowDropUp

    val ArrowDropDown: ImageVector
        @Composable
        get() = Icons.Default.ArrowDropDown

    val NavigateBefore: ImageVector
        @Composable
        get() = Icons.AutoMirrored.Filled.KeyboardArrowLeft

    val NavigateNext: ImageVector
        @Composable
        get() = Icons.AutoMirrored.Filled.KeyboardArrowRight

    val Download: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.download)

    val Today: ImageVector
        @Composable
        get() = Icons.Default.Today

    val Settings: ImageVector
        @Composable
        get() = Icons.Outlined.Settings

    val CheckIndeterminateSmall: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.check_indeterminate_small)

    val BackSpace: ImageVector
        @Composable
        get() = Icons.AutoMirrored.Filled.Backspace

    val FingerPrint: ImageVector
        @Composable
        get() = Icons.Default.Fingerprint

    val Logout: ImageVector
        @Composable
        get() = Icons.AutoMirrored.Filled.Logout

    val Lock: ImageVector
        @Composable
        get() = Icons.Default.Lock

    val Notes: ImageVector
        @Composable
        get() = Icons.AutoMirrored.Filled.Notes

    val AccountBalanceWallet: ImageVector
        @Composable
        get() = Icons.Default.AccountBalanceWallet

    val TrendingDown: ImageVector
        @Composable
        get() = Icons.AutoMirrored.Filled.TrendingDown

    val TrendingUp: ImageVector
        @Composable
        get() = Icons.AutoMirrored.Filled.TrendingUp
}

@Preview
@Composable
fun AppIconsPreview(modifier: Modifier = Modifier) {
    SpendLessTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = Color.White
        ) {
            val icons = listOf(
                AppIcons.WalletMoney to "WalletMoney",
                AppIcons.ArrowBack to "ArrowBack",
                AppIcons.ArrowForward to "ArrowForward",
                AppIcons.Check to "Check",
                AppIcons.Close to "Close",
                AppIcons.Add to "Add",
                AppIcons.ArrowDropUp to "ArrowDropUp",
                AppIcons.ArrowDropDown to "ArrowDropDown",
                AppIcons.NavigateBefore to "NavigateBefore",
                AppIcons.NavigateNext to "NavigateNext",
                AppIcons.Download to "Download",
                AppIcons.Today to "Today",
                AppIcons.Settings to "Settings",
                AppIcons.CheckIndeterminateSmall to "CheckIndeterminateSmall",
                AppIcons.BackSpace to "BackSpace",
                AppIcons.FingerPrint to "FingerPrint",
                AppIcons.Logout to "Logout",
                AppIcons.Lock to "Lock",
                AppIcons.Notes to "Notes",
                AppIcons.AccountBalanceWallet to "AccountBalanceWallet",
                AppIcons.TrendingDown to "TrendingDown",
                AppIcons.TrendingUp to "TrendingUp"
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(5), // You can adjust the number of columns
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(icons) { iconPair ->
                    GridIconItem(icon = iconPair.first, name = iconPair.second)
                }
            }
        }
    }
}

@Composable
fun GridIconItem(icon: ImageVector, name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = name,
            modifier = Modifier.size(32.dp) // Larger icon size for grid
        )
        Text(text = name, modifier = Modifier.padding(top = 4.dp))
    }
}