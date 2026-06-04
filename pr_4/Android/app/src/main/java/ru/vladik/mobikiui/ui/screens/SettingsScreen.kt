package ru.vladik.mobikiui.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vladik.mobikiui.BuildConfig
import ru.vladik.mobikiui.data.StaticData
import ru.vladik.mobikiui.ui.theme.AstrollColors

@Composable
fun SettingsScreen() {
  val rows = StaticData.settings.entries.toList()
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 24.dp)
  ) {
    item { SettingsSectionHeader("О приложении") }
    item {
      ListItem(
        headlineContent = {
          Text(
            "Версия приложения",
            color = AstrollColors.textPrimary,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
          )
        },
        supportingContent = {
          Text(
            "${BuildConfig.VERSION_NAME} · build ${BuildConfig.VERSION_CODE}",
            color = AstrollColors.textDimmed,
            fontSize = 14.sp
          )
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        modifier = Modifier.padding(horizontal = 8.dp)
      )
    }
    item {
      ListItem(
        headlineContent = {
          Text(
            "Оформление",
            color = AstrollColors.textPrimary,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
          )
        },
        supportingContent = {
          Text("Astroll + верстка в духе Telegram", color = AstrollColors.textDimmed, fontSize = 14.sp)
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        modifier = Modifier.padding(horizontal = 8.dp)
      )
    }
    item {
      HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        color = Color.White.copy(alpha = 0.08f)
      )
    }
    item { SettingsSectionHeader("Параметры") }
    items(rows, key = { it.key }) { (k, v) ->
      ListItem(
        headlineContent = {
          Text(k, color = AstrollColors.textPrimary, fontWeight = FontWeight.Medium, fontSize = 16.sp)
        },
        supportingContent = {
          Text(v, color = AstrollColors.textDimmed, fontSize = 14.sp)
        },
        trailingContent = {
          Icon(
            Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = AstrollColors.textDimmed
          )
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        modifier = Modifier.padding(horizontal = 8.dp)
      )
    }
  }
}

@Composable
private fun SettingsSectionHeader(text: String) {
  Text(
    text = text,
    modifier = Modifier.padding(start = 20.dp, top = 18.dp, bottom = 6.dp, end = 16.dp),
    color = AstrollColors.accentSoft,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold
  )
}
