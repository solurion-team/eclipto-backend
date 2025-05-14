**eclipto-backend** 🚀

**Описание проекта**

`eclipto-backend` — это распределённая микросервисная система для управления задачами и рабочими пространствами с расширенным GUI и встроенными инструментами автоматизации на базе GigaChat. Проект предназначен для команд, которым требуется гибкая платформа для постановки, приоритизации и отслеживания задач в рамках различных рабочих пространств.

>ℹ️ Для просмотра визуальной части приложения посетите репозиторий eclipto-frontend: https://github.com/solurion-team/eclipto-frontend

---

## 🎯 Цели и задачи

**Цель:** Создать надёжное и масштабируемое серверное решение для управления задачами с возможностью интеграции интеллектуального описания задач через GigaChat.

**Основные задачи:**

* Форма регистрации пользователей и управление профилем
* Страницы рабочих пространств и задач
* Интерактивный календарь для планирования
* Персонализация интерфейса под потребности пользователя
* Интеграция с GigaChat для автоматической генерации описаний задач

---

## 🚀 Ключевой функционал

1. **Регистрация и аутентификация**

   * Безопасная авторизация по Bearer-токену
   * Управление учётными данными и настройками профиля

2. **Управление задачами**

   * Создание задач (заголовок, описание)
   * Отметка статуса: текущие, выполненные, удалённые
   * Приоритеты и сроки выполнения (выбор даты через календарь)
   * Фильтрация и сортировка списка задач
   * Редактирование метаданных задачи

3. **Рабочие пространства**

   * Разделение задач по проектам/рабочим пространствам
   * Переключение между рабочими пространствами в GUI

4. **Интеграция GigaChat**

   * Автоматическое получение чернового описания задачи по заголовку
   * Возможность принять или откорректировать сгенерированное описание

---

## 🏗️ Архитектура

* **Микросервисы** — Spring Boot (Java & Kotlin)

  * Спецификация OpenAPI разрабатывалась сначала для каждого микросервиса
  * Генерация Java-кода сервисов на основе OpenAPI-спецификаций
  * Логика работы с задачами, рабочими пространствами и пользователями
  * Взаимодействие через Kafka для событийного обмена и каскадного удаления сущностей
  * Поддержка H2 (для тестов) и PostgreSQL (продакшн)

* **API Gateway** — FastAPI (Python)

  * Разработан после микросервисов для единой маршрутизации
  * Валидация и конфигурация через Pydantic & Dynaconf
  * Спецификация OpenAPI автоматически агрегирует эндпоинты микросервисов

* **Сообщения и интеграция**

  * Kafka: асинхронная передача сообщений между компонентами
  * GigaChat: внешнее API для генерации описаний задач

* **Контейнеризация**

  * Каждый сервис упакован в Docker-образ через Dockerfile
  * Развёртывание производится через оркестрацию контейнеров (без Docker Compose)

---

## Технологический стек

![Python](https://img.shields.io/badge/Python-3776AB?style=flat-square\&logo=python\&logoColor=white) ![Java](https://img.shields.io/badge/Java-007396?style=flat-square\&logo=java\&logoColor=white) ![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=flat-square\&logo=kotlin\&logoColor=white) ![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=flat-square\&logo=fastapi\&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square\&logo=spring\&logoColor=white) ![Pydantic](https://img.shields.io/badge/Pydantic-4EA94B?style=flat-square\&logo=pydantic\&logoColor=white) ![Dynaconf](https://img.shields.io/badge/Dynaconf-1299DA?style=flat-square) ![Kafka](https://img.shields.io/badge/Kafka-231F20?style=flat-square\&logo=apachekafka\&logoColor=white) ![H2](https://img.shields.io/badge/H2-004B8D?style=flat-square\&logo=H2database\&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat-square\&logo=postgresql\&logoColor=white) ![OpenAPI](https://img.shields.io/badge/OpenAPI-6CA0DC?style=flat-square\&logo=openapi\&logoColor=white) ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat-square\&logo=swagger\&logoColor=black) ![JWT](https://img.shields.io/badge/JWT-000000?style=flat-square\&logo=jsonwebtoken\&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square\&logo=docker\&logoColor=white) ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white)


---

## 📥 Установка и запуск

> **Требования:** Docker, Python 3.9+, Java 17+, Gradle

1. **Клонируйте репозиторий:**

   ```bash
   git clone https://github.com/solurion-team/eclipto-backend.git
   cd eclipto-backend
   ```

2. **Запустите API Gateway:**

   ```bash
   cd api-gateway
   pip install -r requirements.txt
   uvicorn app.main:app --host 0.0.0.0 --port 8000 --workers 4
   ```

3. **Соберите и запустите микросервисы:**

   ```bash
   cd ../microservices/task
   ./gradlew clean build
   docker build -t eclipto-task .
   docker run -d eclipto-task

   cd ../user
   ./gradlew clean build
   docker build -t eclipto-user .
   docker run -d eclipto-user

   cd ../workspace
   ./gradlew clean build
   docker build -t eclipto-workspace .
   docker run -d eclipto-workspace

   # и другие микросервисы аналогично
   ```

4. **Проверьте доступность API:**

   ```
   http://localhost:8000
   ```

---

