package dev.gaddal.core.presentation.designsystem.colors
// Color.kt

import androidx.compose.ui.graphics.Color

object AppColors {
    val Primary = Color(0xFF5A00C8)
    val OnPrimary = Color(0xFFFFFFFF)
    val PrimaryContainer = Color(0xFF8138FF)
    val OnPrimaryContainer = Color(0xFFFFFFFF)
    val PrimaryFixed = Color(0xFFEADDFF)
    val OnPrimaryFixed = Color(0xFF24005A)
    val PrimaryFixedVariant = Color(0xFF590007)
    val InversePrimary = Color(0xFFD2BCFF)

    val Secondary = Color(0xFF5F6200)
    val SecondaryContainer = Color(0xFFD2E750)
    val OnSecondaryContainer = Color(0xFF414300)
    val SecondaryFixed = Color(0xFFE5EA58)
    val SecondaryFixedDan = Color(0xFFC9CE3E)

    val TertiaryContainer = Color(0xFFC4E0F9)

    val Error = Color(0xFFA40019)
    val OnError = Color(0xFFFFFFFF)

    val Success = Color(0xFF29AC08)

    val Surface = Color(0xFFFCF9F9)
    val SurfaceContainerLowest = Color(0xFFFFFFFF)
    val SurfaceContainerLow = Color(0xFFF6F3F3)
    val SurfaceContainer = Color(0xFFF0EDED)
    val SurfaceContainerHighest = Color(0xFFE4E2E2)

    val OnSurface = Color(0xFF1B1B1C)
    val OnSurfaceVariant = Color(0xFF44474B)
    val Outline = Color(0xFF75777B)
    val InverseSurface = Color(0xFF303031)
    val InverseOnSurface = Color(0xFFF3F0F0)
    val Background = Color(0xFFFEF7FF)
    val OnBackground = Color(0xFF1D1A25)

    // State Layer Colors with opacity:
    val Primary_SL12 = Primary.copy(alpha = 0.12f) // Primary / opacity - 0.12
    val Primary_SL16 = Primary.copy(alpha = 0.16f) // Primary / opacity - 0.16
    val OnPrimary_SL12 = OnPrimary.copy(alpha = 0.12f) // onPrimary / opacity - 0.12

    val PrimaryContainer_SL08 =
        PrimaryContainer.copy(alpha = 0.08f) // primaryContainer / opacity - 0.08

    val OnPrimaryContainer_SL12 =
        OnPrimaryContainer.copy(alpha = 0.12f) // onPrimaryContainer / opacity - 0.12

    val OnSecondaryContainer_SL08 =
        OnSecondaryContainer.copy(alpha = 0.08f) // on Secondary Container / opacity - 0.08
    val OnSecondaryContainer_SL12 =
        OnSecondaryContainer.copy(alpha = 0.12f) // onSecondary Container / opacity - 0.12

    val Error_SL08 = Error.copy(alpha = 0.08f) // Error / opacity - 0.08
    val Error_SL12 = Error.copy(alpha = 0.12f) // Error / opacity - 0.12

    val OnBackground_SL08 = OnBackground.copy(alpha = 0.08f) // onBackground / opacity - 0.08
    val OnBackground_SL12 = OnBackground.copy(alpha = 0.12f) // onBackground / opacity - 0.12

    val OnSurface_SL12 = OnSurface.copy(alpha = 0.12f) // onSurface / opacity - 0.12

    val OnSurfaceVariant_SL12 =
        OnSurfaceVariant.copy(alpha = 0.12f) // onSurfaceVariant / opacity - 0.12
}