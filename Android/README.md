## Android-проект (практические 2 и 3)

Проект на **Kotlin + Jetpack Compose**: мобильный UI с нижней навигацией.

### Практика 2 — интерфейс
- Вкладки: **Чаты / Заметки / Погода / Карта / Настройки**
- Topbar, «стеклянные» карточки, тёмная тема, анимированный фон
- Чаты, погода, карта, настройки — **статические данные** (`app/src/main/java/.../data/StaticData.kt`)

### Практика 3 — локальная БД (SQLite + Room)
- База: **Room** (`db/AppDatabase.kt`, `db/NoteEntity.kt`, `db/NoteDao.kt`)
- **CRUD** в `data/NotesRepository.kt`:
  - **Create** — `createNote(...)` → `INSERT`
  - **Read** — `observeNotes()` (список в UI), `readNote(id)` (одна запись)
  - **Update** — `updateNote(note)`
  - **Delete** — `deleteNote(note)`
- **UI:** `ui/screens/NotesScreen.kt` — поля заголовок/текст/тег, кнопка **«Сохранить заметку»**, список из БД, **«Обновить»** при редактировании, иконки **удалить / редактировать**
- При первом запуске в `MobikiApplication` в БД подставляются стартовые заметки из `StaticData`, если таблица пуста

### Требования для сборки
- **Windows 10/11**
- **JDK 17**
- **Android SDK** (через Android Studio или `sdkmanager`)
- **Gradle** в PATH (или Gradle Wrapper, если добавите в проект)

### Сборка APK через командную строку

Откройте PowerShell в папке `Android/` (рядом с `settings.gradle.kts`).

1) Установка Gradle (если команды `gradle` нет), например через Chocolatey:

```powershell
choco install gradle -y
```

2) Указать Android SDK (если переменная ещё не задана):

```powershell
$env:ANDROID_HOME = "C:\Users\<ВАШ_ПОЛЬЗОВАТЕЛЬ>\AppData\Local\Android\Sdk"
```

3) Сборка **debug** APK:

```powershell
gradle :app:assembleDebug
```

Готовый файл:

`app\build\outputs\apk\debug\app-debug.apk`

4) (Опционально) **release** APK (для установки на устройство обычно нужна подпись):

```powershell
gradle :app:assembleRelease
```

### Архив для сдачи
Заархивируйте папку `Android/` вместе с этим `README.md` (исходники + инструкция по сборке).
