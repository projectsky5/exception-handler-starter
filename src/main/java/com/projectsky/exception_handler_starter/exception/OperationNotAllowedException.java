package com.projectsky.exception_handler_starter.exception;

/**
 * Выбрасывается при попытке выполнить запрещённую по бизнес-логике операцию.
 * Например, доступ к ресурсу без соответствующих прав.
 * Обычно соответствует ответу 403 Forbidden.
 */
public class OperationNotAllowedException extends RuntimeException {
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
