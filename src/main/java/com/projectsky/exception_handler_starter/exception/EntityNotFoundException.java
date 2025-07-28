package com.projectsky.exception_handler_starter.exception;

/**
 * Выбрасывается, когда запрашиваемая сущность не найдена в базе данных.
 * Обычно транслируется в ответ с кодом 404 Not Found.
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
