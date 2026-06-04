# Практическая работа 5 — план публикации приложения Astroll

Учебное Android-приложение **Astroll** (`ru.vladik.mobikiui`): мессенджер-подобный UI, заметки (Room), карта OpenStreetMap (Leaflet), погода (Open-Meteo API).

Исходный код: папка **`../Android/`** (актуальная сборка) и **`../pr_4/Android/`** (дубликат для работы 4).

## Документы по заданию

| Пункт задания | Файл |
|---------------|------|
| 1. План монетизации и публикации | [MONETIZATION_AND_PUBLICATION_PLAN.md](MONETIZATION_AND_PUBLICATION_PLAN.md) |
| 2. Инструкция по сборке APK (CLI) | [BUILD_APK.md](BUILD_APK.md) |
| 3. Средства обеспечения безопасности | [SECURITY.md](SECURITY.md) |
| 4. Сдача (архив / репозиторий) | [SUBMISSION.md](SUBMISSION.md) |

Дополнительно: [USER_GUIDE.md](USER_GUIDE.md) — краткое руководство пользователя (на базе pr_4).

## Открытый репозиторий

Код и документация: **https://github.com/wingelshteyn/Mobile_app**

Ветка `main`: `Android/`, `pr_3/`, `pr_4/`, `pr_5/`.

## Быстрая сборка APK

```powershell
cd "d:\CURSOR PROJECT\ДЗ\Мобилки\Android"
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"
gradle :app:assembleDebug
```

APK: `Android\app\build\outputs\apk\debug\app-debug.apk`

Подробности — в [BUILD_APK.md](BUILD_APK.md).
