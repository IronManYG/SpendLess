package dev.gaddal.settings.presentation

import dev.gaddal.core.presentation.ui.UiText

// PreferencesEvent.kt
sealed interface SettingsEvent {
    data object SettingsSaved : SettingsEvent
    data class Error(val message: UiText) : SettingsEvent
}