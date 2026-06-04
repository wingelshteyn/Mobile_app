package ru.vladik.mobikiui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.StickyNote2
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.CloudQueue
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vladik.mobikiui.ui.AppTab
import ru.vladik.mobikiui.ui.theme.AstrollColors

@Composable
fun TelegramDrawerContent(
  selected: AppTab,
  onSelect: (AppTab) -> Unit
) {
  Column(
    modifier = Modifier
      .width(300.dp)
      .fillMaxHeight()
      .clip(RoundedCornerShape(topEnd = 18.dp, bottomEnd = 18.dp))
      .background(
        brush = Brush.verticalGradient(
          colorStops = arrayOf(
            0f to Color.White.copy(alpha = 0.06f),
            0.5f to Color.White.copy(alpha = 0.02f),
            1f to Color.Black.copy(alpha = 0.12f)
          )
        )
      )
      .background(Color(0xD8080A10))
      .border(1.dp, AstrollColors.glassBorder, RoundedCornerShape(topEnd = 18.dp, bottomEnd = 18.dp))
      .padding(vertical = 16.dp)
  ) {
    Column(Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
      AstrollLogotypeRow(textSize = 26.sp, letterSpacing = 3.sp, diceHorizontalPadding = 1.dp)
      Spacer(Modifier.height(6.dp))
      Text(
        text = "Ролевая платформа",
        color = AstrollColors.textDimmed,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 2.sp
      )
    }
    HorizontalDivider(
      modifier = Modifier.padding(vertical = 8.dp),
      color = Color.White.copy(alpha = 0.08f)
    )
    DrawerItem(
      label = "Чаты",
      selected = selected == AppTab.Chats,
      onClick = { onSelect(AppTab.Chats) },
      icon = { tint -> Icon(Icons.Outlined.ChatBubbleOutline, null, tint = tint) }
    )
    DrawerItem(
      label = "Заметки",
      selected = selected == AppTab.Notes,
      onClick = { onSelect(AppTab.Notes) },
      icon = { tint -> Icon(Icons.AutoMirrored.Outlined.StickyNote2, null, tint = tint) }
    )
    DrawerItem(
      label = "Погода",
      selected = selected == AppTab.Weather,
      onClick = { onSelect(AppTab.Weather) },
      icon = { tint -> Icon(Icons.Outlined.CloudQueue, null, tint = tint) }
    )
    DrawerItem(
      label = "Карта",
      selected = selected == AppTab.Map,
      onClick = { onSelect(AppTab.Map) },
      icon = { tint -> Icon(Icons.Outlined.Map, null, tint = tint) }
    )
    DrawerItem(
      label = "Настройки",
      selected = selected == AppTab.Settings,
      onClick = { onSelect(AppTab.Settings) },
      icon = { tint -> Icon(Icons.Outlined.Settings, null, tint = tint) }
    )
  }
}

@Composable
private fun DrawerItem(
  label: String,
  selected: Boolean,
  onClick: () -> Unit,
  icon: @Composable (tint: Color) -> Unit
) {
  val bg = if (selected) Color.White.copy(alpha = 0.08f) else Color.Transparent
  val fg = if (selected) AstrollColors.accentSelected else AstrollColors.textBody
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 10.dp, vertical = 2.dp)
      .clip(RoundedCornerShape(10.dp))
      .background(bg)
      .clickable(onClick = onClick)
      .padding(horizontal = 12.dp, vertical = 12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    icon(fg)
    Spacer(Modifier.width(14.dp))
    Text(
      text = label,
      color = fg,
      fontSize = 16.sp,
      fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
    )
  }
}
