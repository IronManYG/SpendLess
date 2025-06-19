package dev.gaddal.core.presentation.designsystem.components.util

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.gaddal.core.presentation.designsystem.colors.AppColors.Shadow_SL08
import kotlinx.coroutines.delay

/**
 * Extension function to conditionally apply a modifier.
 *
 * This function allows you to apply a modifier to a Composable only if a certain condition is met.
 *
 * @param condition The condition to check.
 * @param modifier The modifier to apply if the condition is true.
 *
 * @return A new `Modifier` with the specified modifier applied if the condition is true.
 */
inline fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        this.then(modifier())
    } else this
}

/**
 * Adds a drop shadow effect to the composable.
 *
 * This modifier allows you to draw a shadow behind the composable with various customization options.
 *
 * @param shape The shape of the shadow.
 * @param color The color of the shadow.
 * @param blur The blur radius of the shadow
 * @param offsetY The shadow offset along the Y-axis.
 * @param offsetX The shadow offset along the X-axis.
 * @param spread The amount to increase the size of the shadow.
 *
 * @return A new `Modifier` with the drop shadow effect applied.
 */
fun Modifier.dropShadow(
    shape: Shape,
    color: Color = Shadow_SL08,
    blur: Dp = 4.dp,
    offsetY: Dp = 4.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
) = this.drawBehind {

    val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
    val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

    val paint = Paint()
    paint.color = color

    if (blur.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.withSave {
            canvas.translate(offsetX.toPx(), offsetY.toPx())
            canvas.drawOutline(shadowOutline, paint)
        }
    }
}

/**
 * Adds a vertical scrollbar to a composable.
 *
 * This modifier allows you to add a vertical scrollbar to a composable with various customization options.
 *
 * @param scrollState The state of the scrollable content.
 * @param width The width of the scrollbar.
 * @param minHeight The minimum height of the scrollbar.
 * @param maxHeight The maximum height of the scrollbar.
 * @param showScrollBarTrack Whether to show the scrollbar track.
 * @param scrollBarTrackColor The color of the scrollbar track.
 * @param scrollBarColor The color of the scrollbar.
 * @param scrollBarCornerRadius The corner radius of the scrollbar.
 * @param endPadding The padding at the end of the scrollbar.
 * @param startPadding The padding at the start of the scrollbar.
 * @param topPadding The padding from the top edge.
 * @param bottomPadding The padding from the bottom edge.
 * @param alwaysShow Whether to always show the scrollbar.
 * @param fadeOutDelay The delay before fading out the scrollbar (in milliseconds).
 *
 * @return A new `Modifier` with the vertical scrollbar applied.
 */
@Composable
fun Modifier.verticalColumnScrollbar(
    scrollState: ScrollState,
    width: Dp = 4.dp,
    minHeight: Dp = 24.dp,
    maxHeight: Dp? = null,
    showScrollBarTrack: Boolean = true,
    scrollBarTrackColor: Color = Color.Gray,
    scrollBarColor: Color = Color.Black,
    scrollBarCornerRadius: Float = 4f,
    endPadding: Float = 12f,
    startPadding: Float = 0f,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    alwaysShow: Boolean = true,
    fadeOutDelay: Int = 1000,
    position: ScrollBarPosition = ScrollBarPosition.END
): Modifier = composed {
    val actualScrollBarColor = remember(scrollBarColor) { scrollBarColor }
    var isScrollInProgress by remember { mutableStateOf(false) }
    var scrollbarAlpha by remember { mutableFloatStateOf(if (alwaysShow) 1f else 0f) }

    // Animation for the scrollbar
    val scrollbarAlphaAnimated by animateFloatAsState(
        targetValue = scrollbarAlpha,
        animationSpec = tween(durationMillis = 300),
        label = "scrollbarAlpha"
    )

    // Track scrolling state
    LaunchedEffect(scrollState.isScrollInProgress) {
        isScrollInProgress = scrollState.isScrollInProgress
        if (isScrollInProgress) {
            scrollbarAlpha = 1f
        } else if (!alwaysShow) {
            delay(fadeOutDelay.toLong())
            scrollbarAlpha = 0f
        }
    }

    drawWithContent {
        drawContent()

        if (scrollbarAlphaAnimated > 0f && scrollState.maxValue > 0) {
            val viewportHeight = size.height
            val contentHeight = viewportHeight + scrollState.maxValue

            // Convert padding to pixels
            val topPaddingPx = topPadding.toPx()
            val bottomPaddingPx = bottomPadding.toPx()

            // Calculate scrollable height considering top and bottom paddings
            val scrollableHeight = viewportHeight - topPaddingPx - bottomPaddingPx

            // Calculate scrollbar height with constraints
            val calculatedHeight = (viewportHeight / contentHeight) * scrollableHeight
            val maxHeightPx = maxHeight?.toPx() ?: scrollableHeight
            val scrollBarHeight = calculatedHeight
                .coerceAtLeast(minHeight.toPx())
                .coerceAtMost(maxHeightPx)

            // Calculate scroll position with top padding offset
            val availableScrollSpace = scrollableHeight - scrollBarHeight
            val scrollBarOffset = topPaddingPx +
                    (scrollState.value.toFloat() / scrollState.maxValue.toFloat()) * availableScrollSpace

            // Determine x position based on preference
            val xPosition = when (position) {
                ScrollBarPosition.START -> startPadding
                ScrollBarPosition.END -> size.width - width.toPx() - endPadding
            }

            // Draw track if enabled
            if (showScrollBarTrack) {
                drawRoundRect(
                    cornerRadius = CornerRadius(scrollBarCornerRadius),
                    color = scrollBarTrackColor.copy(alpha = 0.6f * scrollbarAlphaAnimated),
                    topLeft = Offset(xPosition, topPaddingPx),
                    size = Size(width.toPx(), scrollableHeight),
                )
            }

            // Preserve color while applying animation alpha
            val finalColor = actualScrollBarColor.copy(
                alpha = actualScrollBarColor.alpha * scrollbarAlphaAnimated
            )

            // Draw scrollbar
            drawRoundRect(
                cornerRadius = CornerRadius(scrollBarCornerRadius),
                color = finalColor,
                topLeft = Offset(xPosition, scrollBarOffset),
                size = Size(width.toPx(), scrollBarHeight)
            )
        }
    }
}

enum class ScrollBarPosition {
    START, END
}