package com.projectsky.exception_handler_starter.exception.transaction;

/**
 * Исключение используется для отката внутренней транзакции (propagation = NESTED).
 * Преднамеренно не обрабатывается хендлером, чтобы не прерывать внешнюю транзакцию.
 */
public class InnerTransactionException extends RuntimeException {
    public InnerTransactionException(String message) {
        super(message);
    }
}
