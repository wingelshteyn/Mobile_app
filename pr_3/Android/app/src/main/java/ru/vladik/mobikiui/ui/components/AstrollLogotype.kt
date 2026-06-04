package ru.vladik.mobikiui.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vladik.mobikiui.ui.theme.AstrollColors

/**
 * Кубик в логотипе — квадрат со стороной = [textSize] (как высота букв «Astr» / «ll»).
 */
@Composable
fun AstrollDiceO(
  modifier: Modifier = Modifier,
  textSize: TextUnit
) {
  val density = LocalDensity.current
  val side: Dp = with(density) { textSize.toDp() }
  Canvas(modifier.size(side)) {
    val s = size.minDimension
    val scale = s / 24f
    val stroke = (1.35f * scale).coerceAtLeast(0.8f)
    val rx = 3.2f * scale
    drawRoundRect(
      color = AstrollColors.textPrimary,
      topLeft = Offset(4.5f * scale, 4.5f * scale),
      size = Size(15f * scale, 15f * scale),
      cornerRadius = CornerRadius(rx, rx),
      style = Stroke(width = stroke)
    )
    val fill = AstrollColors.textPrimary
    val dotR = (1.2f * scale).coerceAtLeast(0.55f)
    fun dot(cx: Float, cy: Float) {
      drawCircle(fill, dotR, Offset(cx * scale, cy * scale))
    }
    dot(9f, 9f)
    dot(15f, 15f)
    dot(15f, 9f)
    dot(9f, 15f)
    dot(12f, 12f)
  }
}

/**
 * «Astr◻ll» — кубик по высоте совпадает с кеглем текста.
 */
@Composable
fun AstrollLogotypeRow(
  modifier: Modifier = Modifier,
  textSize: TextUnit = 20.sp,
  letterSpacing: TextUnit = 2.sp,
  diceHorizontalPadding: Dp = 0.dp
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = "Astr",
      color = AstrollColors.textPrimary,
      fontWeight = FontWeight.ExtraBold,
      fontSize = textSize,
      letterSpacing = letterSpacing,
      lineHeight = textSize
    )
    AstrollDiceO(
      modifier = Modifier.padding(horizontal = diceHorizontalPadding),
      textSize = textSize
    )
    Text(
      text = "ll",
      color = AstrollColors.textPrimary,
      fontWeight = FontWeight.ExtraBold,
      fontSize = textSize,
      letterSpacing = letterSpacing,
      lineHeight = textSize
    )
  }
}
