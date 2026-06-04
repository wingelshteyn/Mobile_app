package ru.vladik.mobikiui.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin
import ru.vladik.mobikiui.ui.theme.AstrollColors

/**
 * Фон как в astroll: shell-градиенты + «орбы» + сетка + частицы (`BackgroundFx.tsx`).
 */
@Composable
fun AstrollBackground(modifier: Modifier = Modifier) {
  val t = rememberInfiniteTransition(label = "astroll-bg")
  val phase = t.animateFloat(
    initialValue = 0f,
    targetValue = (2 * PI).toFloat(),
    animationSpec = infiniteRepeatable(
      animation = tween(18_000, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "phase"
  )

  Box(modifier = modifier.fillMaxSize()) {
    Canvas(Modifier.fillMaxSize()) {
      val w = size.width
      val h = size.height
      val ph = phase.value

      drawRect(
        brush = Brush.verticalGradient(
          colors = listOf(AstrollColors.shellBaseTop, AstrollColors.shellBaseBottom)
        )
      )

      drawCircle(
        brush = Brush.radialGradient(
          colors = listOf(AstrollColors.radial1.copy(alpha = 0.55f), Color.Transparent),
          center = Offset(w * 0.2f, h * 0.1f),
          radius = max(w, h) * 0.95f
        ),
        radius = max(w, h) * 0.95f,
        center = Offset(w * 0.2f, h * 0.1f)
      )
      drawCircle(
        brush = Brush.radialGradient(
          colors = listOf(AstrollColors.radial2.copy(alpha = 0.5f), Color.Transparent),
          center = Offset(w * 0.8f, h * 0.3f),
          radius = max(w, h) * 0.85f
        ),
        radius = max(w, h) * 0.85f,
        center = Offset(w * 0.8f, h * 0.3f)
      )

      fun softOrb(cx: Float, cy: Float, radius: Float, alpha: Float) {
        drawCircle(
          brush = Brush.radialGradient(
            colors = listOf(Color.White.copy(alpha = alpha), Color.Transparent),
            center = Offset(cx, cy),
            radius = radius
          ),
          radius = radius,
          center = Offset(cx, cy)
        )
      }

      val ox = 46f * sin(ph * 0.5f)
      val oy = 34f * cos(ph * 0.5f)
      softOrb(-180f + w * 0.05f + ox, -180f + h * 0.08f + oy, max(w, h) * 0.55f, 0.14f * 0.28f)
      softOrb(w + 220f - w * 0.15f - ox * 0.8f, 60f + h * 0.05f + oy * 0.7f, max(w, h) * 0.52f, 0.13f * 0.28f)
      softOrb(w * 0.18f + ox * 0.6f, h + 240f - h * 0.2f, max(w, h) * 0.58f, 0.12f * 0.28f)
      softOrb(w * 0.86f - ox * 0.5f, h + 260f - h * 0.15f, max(w, h) * 0.56f, 0.12f * 0.28f)
      softOrb(w * 0.56f + ox * 0.4f, h * 0.44f + oy * 0.5f, max(w, h) * 0.5f, 0.10f * 0.28f)

      val gridStep = 72f
      val gridColor = Color.White.copy(alpha = 0.095f)
      var gx = 0f
      while (gx <= w) {
        drawLine(gridColor, Offset(gx, 0f), Offset(gx, h), strokeWidth = 1f)
        gx += gridStep
      }
      var gy = 0f
      while (gy <= h) {
        drawLine(gridColor, Offset(0f, gy), Offset(w, gy), strokeWidth = 1f)
        gy += gridStep
      }
    }

    AndroidView(
      modifier = Modifier.fillMaxSize(),
      factory = { ctx ->
        ParticleFieldView(ctx).apply { pointCount = 220 }
      }
    )
  }
}
