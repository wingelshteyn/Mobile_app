package ru.vladik.mobikiui.data

data class ChatPreview(
  val id: String,
  val title: String,
  val lastMessage: String,
  val time: String,
  val unread: Int
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
  val humidity: String
)

object StaticData {
  val chats = listOf(
    ChatPreview("c1", "Астро-чат", "Завтра созвон в 19:00, ок?", "18:42", 2),
    ChatPreview("c2", "Команда", "Я залил правки в макет", "17:05", 0),
    ChatPreview("c3", "Погода", "Дождь через 2 часа — взять зонт?", "16:11", 1),
    ChatPreview("c4", "Заметки", "Не забудь дописать практическую", "вчера", 0)
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

