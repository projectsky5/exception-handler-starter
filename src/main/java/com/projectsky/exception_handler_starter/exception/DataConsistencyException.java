package com.projectsky.exception_handler_starter.exception;

/**
 * Сигнализирует о нарушении согласованности данных, например,
 * при использовании паттерна Transactional Outbox или проверке связей между таблицами.
 * Обычно транслируется как 409 Conflict.
 */
public class DataConsistencyException extends RuntimeException {
    public DataConsistencyException(String message) {
        super(message);
    }
}
