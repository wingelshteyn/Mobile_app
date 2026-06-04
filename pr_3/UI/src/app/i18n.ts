export const translations = {
  ru: {
    // Navigation
    chats: "Чаты",
    notes: "Заметки",
    weather: "Погода",
    map: "Карта",
    settings: "Настройки",

    // Chats screen
    chatsTitle: "Сообщения",
    searchChats: "Поиск чатов...",
    newChat: "Новый чат",
    online: "В сети",
    typing: "печатает...",

    // Conversation
    typeMessage: "Введите сообщение...",
    send: "Отправить",

    // Notes
    notesTitle: "Мои заметки",
    newNote: "Новая заметка",
    noteTitle: "Заголовок заметки",
    noteContent: "Содержание заметки...",
    save: "Сохранить",
    delete: "Удалить",
    edit: "Редактировать",

    // Weather
    weatherTitle: "Погода",
    currentWeather: "Текущая погода",
    feelsLike: "Ощущается как",
    humidity: "Влажность",
    wind: "Ветер",
    forecast: "Прогноз на 5 дней",

    // Map
    mapTitle: "Карта",
    yourLocation: "Ваше местоположение",

    // Settings
    settingsTitle: "Настройки",
    language: "Язык",
    russian: "Русский",
    english: "English",
    theme: "Тема",
    darkTheme: "Тёмная тема",
    about: "О приложении",
    version: "Версия"
  },
  en: {
    // Navigation
    chats: "Chats",
    notes: "Notes",
    weather: "Weather",
    map: "Map",
    settings: "Settings",

    // Chats screen
    chatsTitle: "Messages",
    searchChats: "Search chats...",
    newChat: "New chat",
    online: "Online",
    typing: "typing...",

    // Conversation
    typeMessage: "Type a message...",
    send: "Send",

    // Notes
    notesTitle: "My Notes",
    newNote: "New note",
    noteTitle: "Note title",
    noteContent: "Note content...",
    save: "Save",
    delete: "Delete",
    edit: "Edit",

    // Weather
    weatherTitle: "Weather",
    currentWeather: "Current Weather",
    feelsLike: "Feels like",
    humidity: "Humidity",
    wind: "Wind",
    forecast: "5-Day Forecast",

    // Map
    mapTitle: "Map",
    yourLocation: "Your Location",

    // Settings
    settingsTitle: "Settings",
    language: "Language",
    russian: "Русский",
    english: "English",
    theme: "Theme",
    darkTheme: "Dark Theme",
    about: "About",
    version: "Version"
  }
};

export type Language = keyof typeof translations;
export type TranslationKey = keyof typeof translations.ru;
