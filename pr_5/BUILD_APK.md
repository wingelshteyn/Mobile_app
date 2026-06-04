# Инструкция по сборке APK (командная строка)

**Практическая работа 5**

Проект: **`Android/`** в корне репозитория (дубликат: `pr_4/Android/`).

Приложение: **Astroll**, `applicationId = ru.vladik.mobikiui`, версия см. `app/build.gradle.kts`.

---

## 1. Требования

| Компонент | Версия / примечание |
|-----------|---------------------|
| ОС | Windows 10/11, macOS или Linux |
| **JDK** | 17 (`java -version`) |
| **Android SDK** | API 35, Build-Tools 34+ ([Android Studio](https://developer.android.com/studio) → SDK Manager) |
| **Gradle** | 8.x в PATH или локальная установка |

### Переменные окружения (Windows PowerShell)

```powershell
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"
$env:ANDROID_SDK_ROOT = $env:ANDROID_HOME
```

Проверка SDK:

```powershell
Test-Path "$env:ANDROID_HOME\platform-tools\adb.exe"
```

---

## 2. Сборка debug APK

### Шаг 1. Перейти в каталог проекта

```powershell
cd "d:\CURSOR PROJECT\ДЗ\Мобилки\Android"
```

### Шаг 2. Установить Gradle (если не найден)

```powershell
choco install gradle -y
```

Или использовать уже установленный Gradle / путь к `.local-tools` из pr_4 (если настраивали ранее).

### Шаг 3. Собрать debug

```powershell
gradle :app:assembleDebug
```

При первой сборке Gradle скачает зависимости — это может занять несколько минут.

### Шаг 4. Результат

```
Android\app\build\outputs\apk\debug\app-debug.apk
```

### Шаг 5. Установка на телефон (опционально)

```powershell
& "$env:ANDROID_HOME\platform-tools\adb.exe" devices
& "$env:ANDROID_HOME\platform-tools\adb.exe" install -r "app\build\outputs\apk\debug\app-debug.apk"
```

На телефоне разрешите **отладку по USB** и подтвердите запрос «Разрешить отладку».

---

## 3. Сборка release APK

```powershell
gradle :app:assembleRelease
```

Файл (без подписи магазина):

```
app\build\outputs\apk\release\app-release-unsigned.apk
```

Для **Google Play** нужен подписанный **AAB**:

```powershell
gradle :app:bundleRelease
```

Подпись — через keystore в `signingConfigs` (см. [документацию Android](https://developer.android.com/studio/publish/app-signing)).

---

## 4. Очистка перед архивацией

```powershell
gradle clean
```

Не включайте в ZIP папки `app/build/`, `.gradle/`, `.local-tools/`, `*.apk` (по желанию APK можно приложить отдельно).

---

## 5. Проверка после сборки

1. Установите APK на эмулятор или телефон.
2. Включите **интернет**.
3. Меню → **Карта** — загрузка OpenStreetMap (Leaflet).
4. Меню → **Погода** — данные с Open-Meteo.
5. Меню → **Заметки** — создание/удаление (локальная БД Room).

---

## 6. Сборка через Android Studio (альтернатива)

1. **File → Open** → папка `Android`.
2. Дождитесь синхронизации Gradle.
3. **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
4. APK: `app/build/outputs/apk/debug/`.

---

## 7. Типичные ошибки

| Ошибка | Решение |
|--------|---------|
| `gradle` не найден | Установить Gradle или указать полный путь к `gradle.bat` |
| SDK license not accepted | `sdkmanager --licenses` |
| `device unauthorized` | Подтвердить отладку на телефоне |
| Карта/погода пустые | Проверить интернет; установить свежий APK (версия 1.6+) |
