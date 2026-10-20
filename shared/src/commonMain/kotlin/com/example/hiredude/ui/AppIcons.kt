package com.example.hiredude.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconCheck(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.2f, size.height * 0.5f)
            lineTo(size.width * 0.45f, size.height * 0.75f)
            lineTo(size.width * 0.8f, size.height * 0.25f)
        }
        drawPath(
            path = path,
            color = tint,
            style = Stroke(width = size.width * 0.15f)
        )
    }
}

@Composable
fun IconLock(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val bodyTop = size.height * 0.45f
        drawRoundRect(
            color = tint,
            topLeft = Offset(size.width * 0.2f, bodyTop),
            size = Size(size.width * 0.6f, size.height * 0.45f),
            cornerRadius = CornerRadius(size.width * 0.1f)
        )
        drawArc(
            color = tint,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(size.width * 0.3f, size.height * 0.15f),
            size = Size(size.width * 0.4f, size.height * 0.4f),
            style = Stroke(width = size.width * 0.12f)
        )
    }
}

@Composable
fun IconSearch(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val radius = size.width * 0.32f
        val center = Offset(size.width * 0.42f, size.height * 0.42f)
        drawCircle(
            color = tint,
            radius = radius,
            center = center,
            style = Stroke(width = size.width * 0.12f)
        )
        drawLine(
            color = tint,
            start = Offset(center.x + radius * 0.7f, center.y + radius * 0.7f),
            end = Offset(size.width * 0.82f, size.height * 0.82f),
            strokeWidth = size.width * 0.12f
        )
    }
}

@Composable
fun IconBookmark(
    tint: Color,
    isFilled: Boolean = false,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.25f, size.height * 0.15f)
            lineTo(size.width * 0.75f, size.height * 0.15f)
            lineTo(size.width * 0.75f, size.height * 0.85f)
            lineTo(size.width * 0.5f, size.height * 0.65f)
            lineTo(size.width * 0.25f, size.height * 0.85f)
            close()
        }
        if (isFilled) {
            drawPath(path = path, color = tint)
        } else {
            drawPath(path = path, color = tint, style = Stroke(width = size.width * 0.12f))
        }
    }
}

@Composable
fun IconVerified(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        drawCircle(color = tint, radius = size.width * 0.45f)
        val checkPath = Path().apply {
            moveTo(size.width * 0.3f, size.height * 0.5f)
            lineTo(size.width * 0.45f, size.height * 0.65f)
            lineTo(size.width * 0.7f, size.height * 0.35f)
        }
        drawPath(path = checkPath, color = Color.White, style = Stroke(width = size.width * 0.12f))
    }
}

@Composable
fun IconChevronRight(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.35f, size.height * 0.25f)
            lineTo(size.width * 0.65f, size.height * 0.5f)
            lineTo(size.width * 0.35f, size.height * 0.75f)
        }
        drawPath(path = path, color = tint, style = Stroke(width = size.width * 0.12f))
    }
}

@Composable
fun IconWarning(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.5f, size.height * 0.15f)
            lineTo(size.width * 0.85f, size.height * 0.82f)
            lineTo(size.width * 0.15f, size.height * 0.82f)
            close()
        }
        drawPath(path = path, color = tint)
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.5f, size.height * 0.4f),
            end = Offset(size.width * 0.5f, size.height * 0.62f),
            strokeWidth = size.width * 0.08f
        )
        drawCircle(
            color = Color.White,
            radius = size.width * 0.04f,
            center = Offset(size.width * 0.5f, size.height * 0.72f)
        )
    }
}
