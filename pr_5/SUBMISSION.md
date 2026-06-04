# Сдача практической работы 5

## Вариант А — открытый репозиторий (рекомендуется)

**Репозиторий:** https://github.com/wingelshteyn/Mobile_app

| Материал | Путь в репозитории |
|----------|-------------------|
| Исходный код Android | `Android/` |
| План монетизации и публикации | `pr_5/MONETIZATION_AND_PUBLICATION_PLAN.md` |
| Сборка APK (CLI) | `pr_5/BUILD_APK.md` |
| Безопасность | `pr_5/SECURITY.md` |
| Обзор pr_5 | `pr_5/README.md` |
| APK (необязательно) | собрать локально → `Android/app/build/outputs/apk/debug/app-debug.apk` |

**Ссылка для отчёта:**

```
https://github.com/wingelshteyn/Mobile_app/tree/main/pr_5
```

---

## Вариант Б — ZIP-архив

Структура архива `pr_5_Фамилия.zip`:

```
pr_5/
  README.md
  MONETIZATION_AND_PUBLICATION_PLAN.md
  BUILD_APK.md
  SECURITY.md
  SUBMISSION.md
  USER_GUIDE.md
  task.md
Android/          ← без build/, .gradle/, .local-tools/
  app/src/...
  build.gradle.kts
  settings.gradle.kts
  ...
app-debug.apk     ← по желанию
```

### Команды подготовки (PowerShell)

```powershell
cd "d:\CURSOR PROJECT\ДЗ\Мобилки\Android"
gradle clean

cd "d:\CURSOR PROJECT\ДЗ\Мобилки"
# Упаковать вручную через Проводник или:
Compress-Archive -Path "pr_5","Android" -DestinationPath "pr_5_submit.zip" -Force
```

Перед архивацией **не включайте:** `UI_UX/`, `**/build/`, `**/.gradle/`, `**/.local-tools/`, `**/node_modules/`, `*.zip` > 100 MB.

---

## Чеклист по заданию

- [x] П.1 — план монетизации и публикации
- [x] П.2 — инструкция сборки APK (CLI)
- [x] П.3 — описание средств безопасности
- [x] П.4 — репозиторий / инструкция по архиву
