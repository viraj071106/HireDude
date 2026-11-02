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
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

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

@Composable
fun IconPerson(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = tint,
            radius = size.width * 0.2f,
            center = Offset(size.width * 0.5f, size.height * 0.32f)
        )
        val path = Path().apply {
            moveTo(size.width * 0.2f, size.height * 0.85f)
            cubicTo(
                size.width * 0.2f, size.height * 0.62f,
                size.width * 0.8f, size.height * 0.62f,
                size.width * 0.8f, size.height * 0.85f
            )
        }
        drawPath(path = path, color = tint, style = Stroke(width = size.width * 0.12f))
    }
}

@Composable
fun IconEducation(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val cap = Path().apply {
            moveTo(size.width * 0.5f, size.height * 0.2f)
            lineTo(size.width * 0.88f, size.height * 0.4f)
            lineTo(size.width * 0.5f, size.height * 0.6f)
            lineTo(size.width * 0.12f, size.height * 0.4f)
            close()
        }
        drawPath(path = cap, color = tint)
        val base = Path().apply {
            moveTo(size.width * 0.28f, size.height * 0.52f)
            lineTo(size.width * 0.28f, size.height * 0.75f)
            cubicTo(
                size.width * 0.28f, size.height * 0.88f,
                size.width * 0.72f, size.height * 0.88f,
                size.width * 0.72f, size.height * 0.75f
            )
            lineTo(size.width * 0.72f, size.height * 0.52f)
        }
        drawPath(path = base, color = tint, style = Stroke(width = size.width * 0.1f))
    }
}

@Composable
fun IconCode(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val left = Path().apply {
            moveTo(size.width * 0.38f, size.height * 0.28f)
            lineTo(size.width * 0.16f, size.height * 0.5f)
            lineTo(size.width * 0.38f, size.height * 0.72f)
        }
        val right = Path().apply {
            moveTo(size.width * 0.62f, size.height * 0.28f)
            lineTo(size.width * 0.84f, size.height * 0.5f)
            lineTo(size.width * 0.62f, size.height * 0.72f)
        }
        drawPath(path = left, color = tint, style = Stroke(width = size.width * 0.12f))
        drawPath(path = right, color = tint, style = Stroke(width = size.width * 0.12f))
    }
}

@Composable
fun IconWork(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        drawRoundRect(
            color = tint,
            topLeft = Offset(size.width * 0.15f, size.height * 0.35f),
            size = Size(size.width * 0.7f, size.height * 0.5f),
            cornerRadius = CornerRadius(size.width * 0.08f),
            style = Stroke(width = size.width * 0.1f)
        )
        val handle = Path().apply {
            moveTo(size.width * 0.35f, size.height * 0.35f)
            lineTo(size.width * 0.35f, size.height * 0.2f)
            lineTo(size.width * 0.65f, size.height * 0.2f)
            lineTo(size.width * 0.65f, size.height * 0.35f)
        }
        drawPath(path = handle, color = tint, style = Stroke(width = size.width * 0.1f))
    }
}

@Composable
fun IconDocument(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        drawRoundRect(
            color = tint,
            topLeft = Offset(size.width * 0.22f, size.height * 0.15f),
            size = Size(size.width * 0.56f, size.height * 0.72f),
            cornerRadius = CornerRadius(size.width * 0.06f),
            style = Stroke(width = size.width * 0.1f)
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.35f, size.height * 0.38f),
            end = Offset(size.width * 0.65f, size.height * 0.38f),
            strokeWidth = size.width * 0.08f
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.35f, size.height * 0.52f),
            end = Offset(size.width * 0.65f, size.height * 0.52f),
            strokeWidth = size.width * 0.08f
        )
    }
}

@Composable
fun IconLink(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        drawLine(
            color = tint,
            start = Offset(size.width * 0.3f, size.height * 0.7f),
            end = Offset(size.width * 0.7f, size.height * 0.3f),
            strokeWidth = size.width * 0.12f
        )
        val arrow = Path().apply {
            moveTo(size.width * 0.42f, size.height * 0.3f)
            lineTo(size.width * 0.7f, size.height * 0.3f)
            lineTo(size.width * 0.7f, size.height * 0.58f)
        }
        drawPath(path = arrow, color = tint, style = Stroke(width = size.width * 0.12f))
    }
}

@Composable
fun IconStar(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.5f, size.height * 0.1f)
            lineTo(size.width * 0.63f, size.height * 0.38f)
            lineTo(size.width * 0.92f, size.height * 0.38f)
            lineTo(size.width * 0.68f, size.height * 0.58f)
            lineTo(size.width * 0.78f, size.height * 0.88f)
            lineTo(size.width * 0.5f, size.height * 0.7f)
            lineTo(size.width * 0.22f, size.height * 0.88f)
            lineTo(size.width * 0.32f, size.height * 0.58f)
            lineTo(size.width * 0.08f, size.height * 0.38f)
            lineTo(size.width * 0.37f, size.height * 0.38f)
            close()
        }
        drawPath(path = path, color = tint)
    }
}

@Composable
fun IconSettings(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = tint,
            radius = size.width * 0.22f,
            center = Offset(size.width * 0.5f, size.height * 0.5f),
            style = Stroke(width = size.width * 0.12f)
        )
        for (i in 0 until 6) {
            val angle = (i * (PI / 3)).toFloat()
            val startX = size.width * 0.5f + cos(angle) * size.width * 0.28f
            val startY = size.height * 0.5f + sin(angle) * size.height * 0.28f
            val endX = size.width * 0.5f + cos(angle) * size.width * 0.42f
            val endY = size.height * 0.5f + sin(angle) * size.height * 0.42f
            drawLine(
                color = tint,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = size.width * 0.1f
            )
        }
    }
}

@Composable
fun IconLogout(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val door = Path().apply {
            moveTo(size.width * 0.55f, size.height * 0.2f)
            lineTo(size.width * 0.2f, size.height * 0.2f)
            lineTo(size.width * 0.2f, size.height * 0.8f)
            lineTo(size.width * 0.55f, size.height * 0.8f)
        }
        drawPath(path = door, color = tint, style = Stroke(width = size.width * 0.1f))
        drawLine(
            color = tint,
            start = Offset(size.width * 0.4f, size.height * 0.5f),
            end = Offset(size.width * 0.85f, size.height * 0.5f),
            strokeWidth = size.width * 0.1f
        )
        val arrow = Path().apply {
            moveTo(size.width * 0.7f, size.height * 0.35f)
            lineTo(size.width * 0.85f, size.height * 0.5f)
            lineTo(size.width * 0.7f, size.height * 0.65f)
        }
        drawPath(path = arrow, color = tint, style = Stroke(width = size.width * 0.1f))
    }
}

@Composable
fun IconBell(
    tint: Color,
    modifier: Modifier = Modifier.size(18.dp)
) {
    Canvas(modifier = modifier) {
        val bell = Path().apply {
            moveTo(size.width * 0.5f, size.height * 0.18f)
            cubicTo(
                size.width * 0.32f, size.height * 0.28f,
                size.width * 0.28f, size.height * 0.6f,
                size.width * 0.22f, size.height * 0.72f
            )
            lineTo(size.width * 0.78f, size.height * 0.72f)
            cubicTo(
                size.width * 0.72f, size.height * 0.6f,
                size.width * 0.68f, size.height * 0.28f,
                size.width * 0.5f, size.height * 0.18f
            )
        }
        drawPath(path = bell, color = tint, style = Stroke(width = size.width * 0.1f))
        drawArc(
            color = tint,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(size.width * 0.4f, size.height * 0.76f),
            size = Size(size.width * 0.2f, size.height * 0.14f),
            style = Stroke(width = size.width * 0.1f)
        )
    }
}
