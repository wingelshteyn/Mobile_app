package ru.vladik.mobikiui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vladik.mobikiui.ui.theme.AstrollColors

@Composable
fun TelegramAppBar(
  title: String,
  onMenuClick: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp, vertical = 6.dp)
      .clip(RoundedCornerShape(14.dp))
      .background(
        brush = Brush.verticalGradient(
          colorStops = arrayOf(
            0f to Color.White.copy(alpha = 0.055f),
            0.48f to Color.White.copy(alpha = 0.018f),
            1f to Color.Black.copy(alpha = 0.05f)
          )
        )
      )
      .background(Color(0x2406080C))
      .border(1.dp, AstrollColors.glassBorder, RoundedCornerShape(14.dp))
      .padding(vertical = 2.dp)
  ) {
    IconButton(
      onClick = onMenuClick,
      modifier = Modifier
        .align(Alignment.CenterStart)
        .padding(start = 2.dp)
        .size(48.dp)
    ) {
      Icon(
        Icons.Outlined.Menu,
        contentDescription = "Меню",
        tint = AstrollColors.textPrimary
      )
    }
    Text(
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxWidth()
        .padding(horizontal = 52.dp),
      text = title,
      color = AstrollColors.textPrimary,
      fontWeight = FontWeight.Bold,
      fontSize = 19.sp,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.titleLarge,
      maxLines = 1
    )
    IconButton(
      onClick = { },
      modifier = Modifier
        .align(Alignment.CenterEnd)
        .padding(end = 2.dp)
        .size(48.dp)
    ) {
      Icon(
        Icons.Outlined.Search,
        contentDescription = "Поиск",
        tint = AstrollColors.textDimmed
      )
    }
  }
}
