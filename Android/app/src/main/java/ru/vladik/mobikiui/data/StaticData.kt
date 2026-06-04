package ru.vladik.mobikiui.data

data class ChatPreview(
  val id: String,
  val title: String,
  val lastMessage: String,
  val time: String,
  val unread: Int,
  val isOnline: Boolean = false,
  val isPinned: Boolean = false,
  val isTyping: Boolean = false
)

data class NoteItem(
  val id: String,
  val title: String,
  val body: String,
  val tag: String
)

data class WeatherCard(
  val city: String,
  val condition: String,
  val tempC: Int,
  val feelsLikeC: Int,
  val wind: String,
  val humidity: String,
  val weatherCode: Int? = null
)

object StaticData {
  val chats = listOf(
    ChatPreview(
      id = "c1",
      title = "Астро-чат",
      lastMessage = "Завтра созвон в 19:00, ок?",
      time = "18:42",
      unread = 2,
      isOnline = true,
      isPinned = true
    ),
    ChatPreview(
      id = "c2",
      title = "Команда UI",
      lastMessage = "Я залил правки в макет ✓",
      time = "17:05",
      unread = 0,
      isOnline = true
    ),
    ChatPreview(
      id = "c3",
      title = "Погода · Томск",
      lastMessage = "Сейчас +12°, вечером дождь",
      time = "16:11",
      unread = 1,
      isPinned = true
    ),
    ChatPreview(
      id = "c4",
      title = "Мама",
      lastMessage = "Не забудь куртку",
      time = "15:20",
      unread = 0,
      isOnline = true
    ),
    ChatPreview(
      id = "c5",
      title = "Заметки",
      lastMessage = "печатает…",
      time = "вчера",
      unread = 0,
      isTyping = true
    ),
    ChatPreview(
      id = "c6",
      title = "Практическая 4",
      lastMessage = "Карта и API подключены",
      time = "пн",
      unread = 3
    )
  )

  val notes = listOf(
    NoteItem("n1", "Практическая 2", "Сверстать UI + статические данные + сборка APK.", "Учёба"),
    NoteItem("n2", "Идеи", "Сделать фон с орбами и лёгким шумом.", "UI"),
    NoteItem("n3", "Список", "1) Чаты 2) Заметки 3) Погода 4) Карта 5) Настройки", "План")
  )

  val weather = WeatherCard(
    city = "Томск",
    condition = "Облачно, возможен дождь",
    tempC = 12,
    feelsLikeC = 9,
    wind = "4 м/с",
    humidity = "68%"
  )

  val settings = linkedMapOf(
    "Язык" to "Русский",
    "Тема" to "Тёмная",
    "Уведомления" to "Включены",
    "Синхронизация" to "Только Wi‑Fi"
  )
}

