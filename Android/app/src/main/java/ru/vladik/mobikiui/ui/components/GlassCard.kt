package ru.vladik.mobikiui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.vladik.mobikiui.ui.theme.AstrollColors

@Composable
fun GlassCard(
  modifier: Modifier = Modifier,
  padding: PaddingValues = PaddingValues(14.dp),
  content: @Composable ColumnScope.() -> Unit
) {
  val shape = RoundedCornerShape(16.dp)
  Column(
    modifier = modifier
      .shadow(24.dp, shape, ambientColor = Color.Black.copy(alpha = 0.45f))
      .clip(shape)
      .background(AstrollColors.panelBgBase, shape)
      .background(
        brush = Brush.verticalGradient(
          colorStops = arrayOf(
            0f to AstrollColors.panelBgA,
            0.48f to AstrollColors.panelBgB,
            1f to AstrollColors.panelBgC
          )
        ),
        shape = shape
      )
      .border(1.dp, AstrollColors.glassBorder, shape)
      .padding(padding),
    content = content
  )
}
