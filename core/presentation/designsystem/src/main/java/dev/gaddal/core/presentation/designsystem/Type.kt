package dev.gaddal.core.presentation.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * FigtreeTypography
 *
 * This file contains a Typography setup for the Figtree font, mapping  custom
 * design spec (e.g., “Body (x-Small)”) to the official Material 3 style names
 * (e.g., bodySmall).
 *
 * Please refer to the comments in each style for details on how it aligns with
 * the design documentation.
 */
val FigtreeTypography = Typography(

    // =========== DISPLAY ===========
    // According to design docs: “Display Large” is 45/52.
    // Material 3 calls this displayLarge.
    displayLarge = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    // “Display Medium” is 36/44 in design; M3 calls it displayMedium.
    displayMedium = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    // If needed, define displaySmall here based on the design specs.

    // =========== HEADLINES ===========
    // “Headline Large” is 32/40 in the design. M3 calls this headlineLarge.
    headlineLarge = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    // “Headline Medium” is 28/34 in the design. M3 calls this headlineMedium.
    headlineMedium = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 34.sp
    ),
    // If needed, define headlineSmall here based on the design specs.

    // =========== TITLES ===========
    // “Title Large” (20/26) in the design. M3 calls this titleLarge.
    titleLarge = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),
    // “Title Medium” (16/24) in the design. M3 calls this titleMedium.
    titleMedium = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    // “Title Small” (14/20) in the design. M3 calls this titleSmall.
    titleSmall = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // =========== BODY ===========
    //  design calls it “Body (Medium),” set to 16/24. 
    // M3’s matching style name is bodyLarge.
    bodyLarge = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    // “Body (Small),” at 14/20 in the design, maps to M3’s bodyMedium.
    bodyMedium = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    // “Body (x-Small),” 12/16 in the design, maps to M3’s bodySmall.
    bodySmall = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    // =========== LABELS ===========
    //  design calls it “Label (Medium)” at 16/24.
    // M3’s equivalent is labelLarge.
    labelLarge = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    // “Label (Small)” at 14/20 maps to M3’s labelMedium.
    labelMedium = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    // If you have “Label (x-Small)” in the design, define labelSmall here:
    labelSmall = TextStyle(
        fontFamily = FigtreeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)