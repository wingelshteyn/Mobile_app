package ru.vladik.mobikiui.ui.weather

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AcUnit
import androidx.compose.material.icons.rounded.Cloud
import androidx.compose.material.icons.rounded.CloudQueue
import androidx.compose.material.icons.rounded.Grain
import androidx.compose.material.icons.rounded.Thunderstorm
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.vladik.mobikiui.ui.theme.AstrollColors

data class WeatherVisualStyle(
  val primaryIcon: ImageVector,
  val secondaryIcon: ImageVector?,
  val primaryTint: Color,
  val secondaryTint: Color,
  val glowColor: Color
)

fun weatherVisualForCode(code: Int?): WeatherVisualStyle = when (code) {
  0 -> WeatherVisualStyle(
    Icons.Rounded.WbSunny,
    null,
    Color(0xFFFFD54F),
    Color(0xFFFFB300),
    Color(0x66FFD54F)
  )
  1, 2, 3 -> WeatherVisualStyle(
    Icons.Rounded.CloudQueue,
    Icons.Rounded.WbSunny,
    Color(0xFFB0BEC5),
    Color(0x66FFD54F),
    Color(0x4489A4C7)
  )
  45, 48 -> WeatherVisualStyle(
    Icons.Rounded.Cloud,
    null,
    Color(0xFF90A4AE),
    Color(0xFF78909C),
    Color(0x4490A4AE)
  )
  51, 53, 55 -> WeatherVisualStyle(
    Icons.Rounded.Grain,
    Icons.Rounded.Cloud,
    Color(0xFF81D4FA),
    Color(0xFF90A4AE),
    Color(0x4481D4FA)
  )
  61, 63, 65, 80, 81, 82 -> WeatherVisualStyle(
    Icons.Rounded.WaterDrop,
    Icons.Rounded.Cloud,
    Color(0xFF4FC3F7),
    Color(0xFF78909C),
    Color(0x554FC3F7)
  )
  71, 73, 75 -> WeatherVisualStyle(
    Icons.Rounded.AcUnit,
    Icons.Rounded.Cloud,
    Color(0xFFE1F5FE),
    Color(0xFF90A4AE),
    Color(0x55E1F5FE)
  )
  95, 96, 99 -> WeatherVisualStyle(
    Icons.Rounded.Thunderstorm,
    Icons.Rounded.Cloud,
    Color(0xFFFFEE58),
    Color(0xFF546E7A),
    Color(0x66FFEE58)
  )
  else -> WeatherVisualStyle(
    Icons.Rounded.Cloud,
    null,
    AstrollColors.textDimmed,
    AstrollColors.textMuted,
    Color(0x33FFFFFF)
  )
}

@Composable
fun AnimatedWeatherIcon(
  weatherCode: Int?,
  modifier: Modifier = Modifier,
  size: Dp = 96.dp
) {
  val style = weatherVisualForCode(weatherCode)
  val transition = rememberInfiniteTransition(label = "weather")
  val floatY by transition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(2200, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "float"
  )
  val pulse by transition.animateFloat(
    initialValue = 0.92f,
    targetValue = 1.08f,
    animationSpec = infiniteRepeatable(
      animation = tween(1600, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse"
  )
  val offsetY = if (weatherCode == 0) 0.dp else (floatY * 6f - 3f).dp
  val iconScale = if (weatherCode == 0) pulse else 1f

  Box(
    modifier = modifier.size(size),
    contentAlignment = Alignment.Center
  ) {
    Icon(
      imageVector = style.primaryIcon,
      contentDescription = null,
      tint = style.primaryTint,
      modifier = Modifier
        .size(size * iconScale)
        .offset(y = offsetY)
    )
    style.secondaryIcon?.let { secondary ->
      Icon(
        imageVector = secondary,
        contentDescription = null,
        tint = style.secondaryTint,
        modifier = Modifier
          .size(size * 0.42f)
          .offset(x = size * 0.28f, y = size * 0.22f + offsetY * 0.5f)
      )
    }
  }
}
