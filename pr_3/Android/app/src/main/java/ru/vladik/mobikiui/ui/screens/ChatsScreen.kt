package ru.vladik.mobikiui.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue
import ru.vladik.mobikiui.data.ChatPreview
import ru.vladik.mobikiui.data.StaticData
import ru.vladik.mobikiui.ui.theme.AstrollColors

private val avatarPalette = listOf(
  Color(0xFF5A8FC4),
  Color(0xFF6B9B7A),
  Color(0xFF8B6BB3),
  Color(0xFFB38A5C),
  Color(0xFF5C8A9E),
  Color(0xFF9B6B7A)
)

@Composable
fun ChatsScreen() {
  val chats = StaticData.chats
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 8.dp),
    verticalArrangement = Arrangement.Top
  ) {
    itemsIndexed(chats, key = { _, c -> c.id }) { index, chat ->
      TelegramChatRow(chat = chat)
      if (index < chats.lastIndex) {
        HorizontalDivider(
          modifier = Modifier.padding(start = 76.dp),
          thickness = 0.5.dp,
          color = Color.White.copy(alpha = 0.07f)
        )
      }
    }
  }
}

@Composable
private fun TelegramChatRow(chat: ChatPreview) {
  val avatarColor = avatarPalette[chat.id.hashCode().absoluteValue % avatarPalette.size]
  val letter = chat.title.trim().take(1).uppercase().ifEmpty { "?" }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { }
      .padding(horizontal = 12.dp, vertical = 10.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .size(52.dp)
        .clip(CircleShape)
        .background(avatarColor),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = letter,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
      )
    }
    Column(
      modifier = Modifier
        .weight(1f)
        .padding(start = 12.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          modifier = Modifier.weight(1f),
          text = chat.title,
          color = AstrollColors.textPrimary,
          fontWeight = FontWeight.SemiBold,
          fontSize = 17.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Text(
          text = chat.time,
          color = AstrollColors.textDimmed,
          fontSize = 14.sp,
          modifier = Modifier.padding(start = 8.dp)
        )
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 3.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          modifier = Modifier.weight(1f),
          text = chat.lastMessage,
          color = AstrollColors.textDimmed,
          fontSize = 15.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        if (chat.unread > 0) {
          Box(
            modifier = Modifier
              .padding(start = 8.dp)
              .clip(CircleShape)
              .background(Color(0xFF3390EC))
              .padding(horizontal = 7.dp, vertical = 3.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = if (chat.unread > 99) "99+" else chat.unread.toString(),
              color = Color.White,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }
  }
}
