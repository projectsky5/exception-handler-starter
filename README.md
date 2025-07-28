# Exception Handler Starter

## Описание

`exception-handler-starter` — Spring Boot стартер, который предоставляет готовую реализацию глобального обработчика исключений для REST API. Он упрощает обработку типовых ошибок, избавляя от необходимости вручную писать однотипные `@ExceptionHandler`'ы в каждом проекте.

---

## Что включает в себя

Обработчик по умолчанию (`StarterExceptionHandler`) поддерживает:

| Исключение                            | HTTP Статус         | Описание                                                                 |
|---------------------------------------|----------------------|--------------------------------------------------------------------------|
| `MethodArgumentNotValidException`     | `400 Bad Request`     | Ошибка валидации входных параметров                                     |
| `EntityNotFoundException`             | `404 Not Found`       | Сущность не найдена в базе                                               |
| `EntityAlreadyExistsException`        | `409 Conflict`        | Попытка создать уже существующую сущность                                |
| `DataConsistencyException`            | `409 Conflict`        | Нарушена согласованность данных (например, при Transactional Outbox)        |
| `OperationNotAllowedException`        | `403 Forbidden`       | Бизнес правило не позволяет выполнить операцию                           |
| `ExternalServiceException`            | `502 Bad Gateway`     | Ошибка при обращении к внешнему API или микросервису                    |
| `Exception` (прочие)                  | `500 Internal Error`  | Fallback для всех необработанных исключений                                |

---

## Как подключить

### 1. Установите стартер в локальный .m2 репозиторий

```console
mvn clean install
```

### 2. Добавьте зависимость в `pom.xml`

```xml
<dependency>
  <groupId>com.projectsky</groupId>
  <artifactId>exception-handler-starter</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### 3. Создайте свой `@RestControllerAdvice`

```java
@RestControllerAdvice
public class GlobalExceptionHandler extends StarterExceptionHandler {
    // можете добавлять свои @ExceptionHandler-ы
}
```

---

## Как использовать

- Все исключения уже возвращаются в виде `ErrorResponse`, содержащего поля:
  - `status` — HTTP статус
  - `message` — Текст ошибки
  - `timestamp` — Время когда произошла ошибка
  - `path` — Путь запроса
  - `subErrors` — Список подробностей (Ошибки валидации)

- Структура ошибки при валидации:
```json
{
  "status": 400,
  "message": "Validation failed",
  "timestamp": "2025-07-28T13:33:57.123",
  "path": "/api/user",
  "subErrors": [
    {
      "object": "userRequest",
      "field": "email",
      "rejectedValue": "not email",
      "message": "must be a well-formed email address"
    }
  ]
}
```

---

## Используемые технологии

- Java 21
- Spring Boot 3.5.4
- Spring Web
- Spring Validation
- Lombok

---

## Контакты

Разработчик: [telegram](https://t.me/avcherepanov7)

Если вы нашли баг или хотите предложить улучшение - создайте issue или отправьте PR.
