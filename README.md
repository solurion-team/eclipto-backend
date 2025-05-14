**eclipto-backend** 🚀

**Описание проекта**

`eclipto-backend` — это распределённая микросервисная система для управления задачами и рабочими пространствами с расширенным GUI и встроенными инструментами автоматизации на базе GigaChat. Проект предназначен для команд, которым требуется гибкая платформа для постановки, приоритизации и отслеживания задач в рамках различных рабочих пространств.

ℹ️ Для просмотра визуальной части приложения посетите репозиторий eclipto-frontend: https://github.com/solurion-team/eclipto-frontend

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

* **API Gateway** — FastAPI (Python)

  * Управляет маршрутизацией запросов к микросервисам
  * Валидация и конфигурация через Pydantic & Dynaconf
  * Спецификация OpenAPI автоматически генерируется для всех эндпоинтов

* **Микросервисы** — Spring Boot (Java & Kotlin)

  * Логика работы с задачами, рабочими пространствами и пользователями
  * Взаимодействие через Kafka для событийного обмена
  * Поддержка H2 (для тестов) и PostgreSQL (продакшн)

* **Сообщения и интеграция**

  * Kafka: асинхронная передача сообщений между компонентами, используется для каскадного удаления сущностей
  * GigaChat: внешнее API для генерации описаний задач

* **Контейнеризация**

  * Каждый сервис упакован в Docker-образ через Dockerfile
  * Развёртывание производится через оркестрацию контейнеров (без Docker Compose)

---

## Технологический стек

![Python](https://img.shields.io/badge/Python-3776AB?style=flat-square\&logo=python\&logoColor=white) ![Java](https://img.shields.io/badge/Java-007396?style=flat-square\&logo=java\&logoColor=white) ![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=flat-square\&logo=kotlin\&logoColor=white) ![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=flat-square\&logo=fastapi\&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square\&logo=spring\&logoColor=white) ![Pydantic](https://img.shields.io/badge/Pydantic-4EA94B?style=flat-square\&logo=pydantic\&logoColor=white) ![Dynaconf](https://img.shields.io/badge/Dynaconf-1299DA?style=flat-square) ![Kafka](https://img.shields.io/badge/Kafka-231F20?style=flat-square\&logo=apachekafka\&logoColor=white) ![H2](https://img.shields.io/badge/H2-004B8D?style=flat-square\&logo=H2database\&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat-square\&logo=postgresql\&logoColor=white) ![OpenAPI](https://img.shields.io/badge/OpenAPI-6CA0DC?style=flat-square\&logo=openapi\&logoColor=white) ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat-square\&logo=swagger\&logoColor=black) ![JWT](https://img.shields.io/badge/JWT-000000?style=flat-square\&logo=jsonwebtoken\&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square\&logo=docker\&logoColor=white)

---

## 📥 Установка и запуск

> **Требования:** Docker, Python 3.9+, Java 11+, Maven

1. Клонируйте репозиторий:

   ```bash
   git clone https://github.com/your-org/eclipto-backend.git
   cd eclipto-backend
   ```

2. Соберите и запустите образ API Gateway:

   ```bash
   cd gateway
   docker build -t eclipto-gateway .
   docker run -d -p 8000:8000 eclipto-gateway
   ```

3. Соберите и запустите микросервисы:

   ```bash
   cd ../services/task-service
   mvn clean package
   docker build -t eclipto-task .
   docker run -d eclipto-task
   # Повторите для других сервисов
   ```

4. Проверьте доступность API по адресу:

   ```
   http://localhost:8000/docs
   ```

---

