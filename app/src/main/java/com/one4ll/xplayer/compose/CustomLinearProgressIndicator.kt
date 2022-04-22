package com.one4ll.xplayer.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


// Todo Replacement with Material3 LinearProgressIndicator?
@Composable
fun CustomLinearProgressIndicator(
    /*@FloatRange(from = 0.0, to = 1.0)*/
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = color.copy(alpha = IndicatorBackgroundOpacity)
) {
    Canvas(
        modifier
            .progressSemantics(progress)
            .size(LinearIndicatorWidth, LinearIndicatorHeight)
    ) {
        val strokeWidth = size.height
        drawLinearIndicatorBackground(backgroundColor, strokeWidth)
        drawLinearIndicator(0f, progress, color, strokeWidth)
    }
}

private fun DrawScope.drawLinearIndicatorBackground(
    color: Color,
    strokeWidth: Float
) = drawLinearIndicator(0f, 1f, color, strokeWidth)

private fun DrawScope.drawLinearIndicator(
    startFraction: Float,
    endFraction: Float,
    color: Color,
    strokeWidth: Float
) {
    val width = size.width
    val height = size.height
    // Start drawing from the vertical center of the stroke
    val yOffset = height / 2

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    // Progress line
    drawLine(color, Offset(barStart, yOffset), Offset(barEnd, yOffset), strokeWidth)
}

// LinearProgressIndicator Material specs
// the width should be 240dp here.
private val LinearIndicatorHeight = 4.dp
private val LinearIndicatorWidth = 240.dp
const val IndicatorBackgroundOpacity = 0.24f
