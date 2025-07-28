package com.projectsky.exception_handler_starter.exception;

/**
 * Выбрасывается при попытке создать сущность, которая уже существует.
 * Обычно транслируется в ответ с кодом 409 Conflict.
 */
public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
