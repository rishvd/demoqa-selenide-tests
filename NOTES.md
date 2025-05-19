# 🧠 Заметка: С какими проблемами я столкнулся и как их решил  
_Проект: Автотесты на Selenide с Gradle и Page Object, заливка в GitHub_

---

## 🔹 1. **Ошибка `package com.codeborne.selenide does not exist`**

### ❌ Проблема:
Gradle не видел библиотеку `Selenide`, хотя я подключил её через `testImplementation`.

### 🛠 Решение:
- Либо перенести `PracticeFormPage.java` в `src/test/java/pages`
- **Либо (лучшее)** — поменять зависимость на `implementation` в `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.codeborne:selenide:7.2.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}
```

---

## 🔹 2. **Ошибки `cannot find symbol: method $(...)`, `SelenideElement` и `Condition`**

### ❌ Проблема:
Эти ошибки возникают, когда Selenide не подгрузился, а ты используешь его классы в `src/main/java`.

### 🛠 Решение:
- Убедиться, что Selenide подключён как `implementation`
- Проверить `External Libraries` в IntelliJ — там должна быть `selenide-7.x.x`
- Выполнить: `./gradlew --refresh-dependencies` и `./gradlew clean build`

---

## 🔹 3. **Gradle не подтягивал зависимости**

### ❌ Проблема:
Даже при правильном `build.gradle.kts`, Selenide не скачивался.

### 🛠 Решение:
- Убедиться, что файл открыт как Gradle-проект (`File → Open → папка проекта`)
- Запустить `./gradlew build` или `test` через терминал
- Проверить, что в `repositories` есть `mavenCentral()`

---

## 🔹 4. **Ошибка `git push` — rejected (fetch first)**

### ❌ Проблема:
На GitHub уже был 1 коммит (например, `README.md`), а локальный репозиторий о нём не знал.

### 🛠 Решение:
#### Вариант 1 (перезаписать всё локальным содержимым):
```bash
git push -u origin main --force
```

#### Вариант 2 (сделать pull с разрешением истории):
```bash
git pull origin main --allow-unrelated-histories
git push -u origin main
```

---

## 🔹 5. **Ошибка `remote: Support for password authentication was removed`**

### ❌ Проблема:
GitHub больше не принимает логин/пароль — нужен токен.

### 🛠 Решения:
#### Вариант 1 (через HTTPS):
- Сгенерировать **Personal Access Token** на GitHub и ввести вместо пароля

#### Вариант 2 (лучший — через SSH):
- Сгенерировать SSH-ключ:
  ```bash
  ssh-keygen -t rsa -b 4096 -C "you@example.com"
  ```
- Добавить ключ в GitHub (https://github.com/settings/keys)
- Подключить репозиторий по SSH:
  ```bash
  git remote set-url origin git@github.com:USERNAME/REPO.git
  ```

---

## 🔹 6. **Ошибка `git add` не добавляет ничего**

### ❌ Проблема:
Команда `git add` без аргументов не знает, что добавлять.

### ✅ Решение:
```bash
git add .
```
или
```bash
git add src build.gradle.kts settings.gradle.kts .gitignore
```

---

## 📘 Общие выводы и рекомендации

✅ Подключай библиотеки, которые используются в `main`, через `implementation`, а не `testImplementation`  
✅ Проверяй зависимости через `External Libraries` в IntelliJ  
✅ Структурируй проект по папкам:
```
src/main/java/pages/      — Page Object'ы
src/test/java/            — JUnit тесты
```

✅ Используй `.gitignore`, чтобы не засорять репозиторий служебными файлами  
✅ Лучше всего работать с GitHub через SSH
