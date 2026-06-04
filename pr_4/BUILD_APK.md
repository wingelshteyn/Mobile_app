# Инструкция по сборке APK (командная строка)

Проект: **`pr_4/Android/`** (или `pr_3/Android/` — те же исходники).

## Требования

- **Windows 10/11** (или Linux/macOS с аналогичными командами)
- **JDK 17** (`java -version`)
- **Android SDK** (через [Android Studio](https://developer.android.com/studio) → SDK Manager)
- **Gradle** в PATH **или** Gradle Wrapper в проекте

Переменная окружения (подставьте свой путь к SDK):

```powershell
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"
```

---

## Сборка debug APK

1. Откройте PowerShell в каталоге **`Android`** (рядом с `settings.gradle.kts`):

```powershell
cd "d:\CURSOR PROJECT\ДЗ\Мобилки\pr_4\Android"
```

2. Установите Gradle, если команда `gradle` не найдена:

```powershell
choco install gradle -y
```

3. Соберите debug-сборку:

```powershell
gradle :app:assembleDebug
```

4. Готовый файл:

```
app\build\outputs\apk\debug\app-debug.apk
```

Установка на подключённое устройство (опционально):

```powershell
& "$env:ANDROID_HOME\platform-tools\adb.exe" install -r app\build\outputs\apk\debug\app-debug.apk
```

---

## Сборка release APK

```powershell
gradle :app:assembleRelease
```

Файл: `app\build\outputs\apk\release\app-release-unsigned.apk`

Для установки на телефон release обычно **подписывают** keystore. Для учебной сдачи чаще достаточно **debug** APK.

---

## Сборка через Android Studio

1. **File → Open** → папка `Android`.
2. Дождитесь синхронизации Gradle.
3. **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
4. APK появится в `app/build/outputs/apk/debug/`.

---

## Проверка после сборки

1. Установите APK на эмулятор/телефон.
2. Откройте меню → **Карта** (должна загрузиться OSM).
3. Откройте **Погода** (при включённом интернете — цифры с Open-Meteo).

---

## Архив для сдачи

Включите в ZIP:

- папку **`Android/`** (без `app/build/` и `.gradle/` — можно удалить перед архивацией для уменьшения размера);
- **`USER_GUIDE.md`**, **`BUILD_APK.md`**, **`README.md`**;
- по желанию — готовый **`app-debug.apk`**.

Пример очистки build-артефактов перед архивацией:

```powershell
gradle clean
```
