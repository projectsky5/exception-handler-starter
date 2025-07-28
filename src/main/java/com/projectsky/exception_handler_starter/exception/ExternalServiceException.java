package com.projectsky.exception_handler_starter.exception;

/**
 * Ошибка при взаимодействии с внешним API или микросервисом.
 * Может быть выброшена при ошибке Feign, RestTemplate и т.д.
 * Обычно транслируется как 502 Bad Gateway.
 */
public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String message) {
        super(message);
    }
}
