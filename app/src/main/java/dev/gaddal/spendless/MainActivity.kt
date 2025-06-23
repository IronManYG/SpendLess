@file:OptIn(ExperimentalMaterial3Api::class)

package dev.gaddal.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.gaddal.auth.presentation.register.RegisterRoot
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.designsystem.components.SpendLessScaffold
import dev.gaddal.core.presentation.designsystem.components.SpendLessToolbar
import dev.gaddal.settings.presentation.SettingsAction
import dev.gaddal.settings.presentation.SettingsViewModel
import dev.gaddal.settings.presentation.components.Preferences
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
                var showPreferences by remember { mutableStateOf(false) } // only for testing
                var showHomeScreen by remember { mutableStateOf(false) }

                if (!showPreferences && !showHomeScreen) {
                    RegisterRoot(
                        onSuccessfulRegistration = {
                            showPreferences = true
                        },
                        onAlreadyHaveAnAccountClick = {}
                    )
                }

                if (showPreferences) {
                    val viewModel: SettingsViewModel = getViewModel()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    // Show the preferences screen
                    SpendLessScaffold(
                        topAppBar = {
                            SpendLessToolbar(
                                modifier = Modifier.fillMaxWidth(),
                                showBackButton = true,
                                showDownloadButton = false,
                                showSettingsButton = false,
                                onBackClick = {},
                            )
                        },
                    ) { innerPadding ->
                        Preferences(
                            state = state,
                            onAction = { action ->
                                if (action is SettingsAction.OnStartTrackingClick) {
                                    showPreferences = false
                                    showHomeScreen = true
                                }
                                viewModel.onAction(action)
                            },
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                        )
                    }
                }

                if (showHomeScreen && !showPreferences) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Placeholder for the home screen content
                        // Replace with actual home screen composable
                        Text(text = "Home Screen Placeholder", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}