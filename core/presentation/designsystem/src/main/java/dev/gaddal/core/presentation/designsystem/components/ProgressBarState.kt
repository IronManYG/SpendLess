package dev.gaddal.core.presentation.designsystem.components

sealed class ProgressBarState{

    object Loading: ProgressBarState()

    object Idle: ProgressBarState()
}