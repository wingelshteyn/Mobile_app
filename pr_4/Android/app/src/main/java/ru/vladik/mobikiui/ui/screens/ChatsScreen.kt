package ru.vladik.mobikiui.ui.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue
import ru.vladik.mobikiui.data.ChatPreview
import ru.vladik.mobikiui.data.StaticData
import ru.vladik.mobikiui.ui.theme.AstrollColors

private val avatarGradients = listOf(
  listOf(Color(0xFF5B8DEF), Color(0xFF3D5AFE)),
  listOf(Color(0xFF26A69A), Color(0xFF00897B)),
  listOf(Color(0xFFAB47BC), Color(0xFF7B1FA2)),
  listOf(Color(0xFFFF7043), Color(0xFFE64A19)),
  listOf(Color(0xFF42A5F5), Color(0xFF1565C0)),
  listOf(Color(0xFFEC407A), Color(0xFFC2185B))
)

@Composable
fun ChatsScreen() {
  val chats = StaticData.chats
  val pinned = chats.filter { it.isPinned }
  val regular = chats.filterNot { it.isPinned }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 16.dp),
    verticalArrangement = Arrangement.spacedBy(6.dp)
  ) {
    item {
      MessengerSearchBar(modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp))
    }
    if (pinned.isNotEmpty()) {
      item {
        SectionLabel(
          text = "Закреплённые",
          icon = Icons.Outlined.PushPin,
          modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
        )
      }
      items(pinned, key = { it.id }) { chat ->
        MessengerChatCard(chat = chat, modifier = Modifier.padding(horizontal = 12.dp))
      }
    }
    item {
      SectionLabel(
        text = "Все чаты",
        modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
      )
    }
    items(regular, key = { it.id }) { chat ->
      MessengerChatCard(chat = chat, modifier = Modifier.padding(horizontal = 12.dp))
    }
  }
}

@Composable
private fun MessengerSearchBar(modifier: Modifier = Modifier) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .background(Color(0xFF12151C))
      .border(1.dp, AstrollColors.glassBorder, RoundedCornerShape(14.dp))
      .padding(horizontal = 14.dp, vertical = 12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      Icons.Outlined.Search,
      contentDescription = null,
      tint = AstrollColors.textDimmed,
      modifier = Modifier.size(20.dp)
    )
    Spacer(Modifier.width(10.dp))
    Text(
      text = "Поиск по чатам и сообщениям",
      color = AstrollColors.textDimmed,
      fontSize = 15.sp
    )
    Spacer(Modifier.weight(1f))
    Box(
      modifier = Modifier
        .size(36.dp)
        .clip(CircleShape)
        .background(
          brush = Brush.linearGradient(
            colors = listOf(Color(0xFF3390EC), Color(0xFF2B7CD3))
          )
        ),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        Icons.Outlined.Edit,
        contentDescription = "Новое сообщение",
        tint = Color.White,
        modifier = Modifier.size(18.dp)
      )
    }
  }
}

@Composable
private fun SectionLabel(
  text: String,
  modifier: Modifier = Modifier,
  icon: androidx.compose.ui.graphics.vector.ImageVector? = null
) {
  Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
    icon?.let {
      Icon(it, contentDescription = null, tint = AstrollColors.textDimmed, modifier = Modifier.size(14.dp))
      Spacer(Modifier.width(6.dp))
    }
    Text(
      text = text.uppercase(),
      color = AstrollColors.textDimmed,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )
  }
}

@Composable
private fun MessengerChatCard(chat: ChatPreview, modifier: Modifier = Modifier) {
  val gradient = avatarGradients[chat.id.hashCode().absoluteValue % avatarGradients.size]
  val letter = chat.title.trim().take(1).uppercase().ifEmpty { "?" }

  Row(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .background(
        brush = Brush.verticalGradient(
          colors = listOf(
            Color(0xFF161A22),
            Color(0xFF10141B)
          )
        )
      )
      .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(16.dp))
      .clickable { }
      .padding(horizontal = 12.dp, vertical = 11.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(contentAlignment = Alignment.BottomEnd) {
      Box(
        modifier = Modifier
          .size(54.dp)
          .clip(CircleShape)
          .background(Brush.linearGradient(gradient)),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = letter,
          color = Color.White,
          fontWeight = FontWeight.Bold,
          fontSize = 22.sp
        )
      }
      if (chat.isOnline) {
        Box(
          modifier = Modifier
            .offset(x = 2.dp, y = 2.dp)
            .size(14.dp)
            .clip(CircleShape)
            .background(Color(0xFF0E1016))
            .padding(2.dp)
            .clip(CircleShape)
            .background(Color(0xFF4CD964))
        )
      }
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
          fontSize = 16.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Text(
          text = chat.time,
          color = if (chat.unread > 0) Color(0xFF3390EC) else AstrollColors.textDimmed,
          fontSize = 13.sp,
          modifier = Modifier.padding(start = 8.dp)
        )
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        if (chat.isTyping) {
          TypingIndicator()
          Spacer(Modifier.width(6.dp))
        }
        Text(
          modifier = Modifier.weight(1f),
          text = chat.lastMessage,
          color = if (chat.isTyping) Color(0xFF3390EC) else AstrollColors.textDimmed,
          fontSize = 14.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        if (chat.unread > 0) {
          UnreadBadge(chat.unread)
        }
      }
    }
  }
}

@Composable
private fun TypingIndicator() {
  val transition = rememberInfiniteTransition(label = "typing")
  Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
    repeat(3) { index ->
      val alpha by transition.animateFloat(
        initialValue = 0.25f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
          animation = tween(500, delayMillis = index * 120),
          repeatMode = RepeatMode.Reverse
        ),
        label = "dot$index"
      )
      Box(
        modifier = Modifier
          .size(5.dp)
          .clip(CircleShape)
          .background(Color(0xFF3390EC).copy(alpha = alpha))
      )
    }
  }
}

@Composable
private fun UnreadBadge(count: Int) {
  Box(
    modifier = Modifier
      .padding(start = 8.dp)
      .height(22.dp)
      .clip(RoundedCornerShape(11.dp))
      .background(
        brush = Brush.horizontalGradient(
          colors = listOf(Color(0xFF3390EC), Color(0xFF2B7CD3))
        )
      )
      .padding(horizontal = 8.dp),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = if (count > 99) "99+" else count.toString(),
      color = Color.White,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold
    )
  }
}
