package com.projectsky.exception_handler_starter.handler;

import com.projectsky.exception_handler_starter.dto.ErrorResponse;
import com.projectsky.exception_handler_starter.dto.SubError;
import com.projectsky.exception_handler_starter.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Базовый класс обработчика исключений для REST-контроллеров.
 * Предоставляет стандартную обработку следующих исключений:
 * <ul>
 *     <li>{@link MethodArgumentNotValidException} — 400 Bad Request</li>
 *     <li>{@link EntityNotFoundException} — 404 Not Found</li>
 *     <li>{@link EntityAlreadyExistsException} — 409 Conflict</li>
 *     <li>{@link DataConsistencyException} — 409 Conflict</li>
 *     <li>{@link OperationNotAllowedException} — 403 Forbidden</li>
 *     <li>{@link ExternalServiceException} — 502 Bad Gateway</li>
 *     <li>{@link Exception} — 500 Internal Server Error (fallback)</li>
 * </ul>
 * <p>
 * Используется как базовый класс для аннотированного {@code @RestControllerAdvice}.
 */
@Slf4j
public class StarterExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        List<SubError> subErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> SubError.builder()
                        .object(error.getObjectName())
                        .field(error.getField())
                        .rejectedValue(error.getRejectedValue())
                        .message(error.getDefaultMessage())
                        .build())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Validation failed")
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .subErrors(subErrors)
                        .build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .subErrors(Collections.emptyList())
                        .build());
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .subErrors(Collections.emptyList())
                        .build());
    }

    @ExceptionHandler(DataConsistencyException.class)
    public ResponseEntity<ErrorResponse> handleDataConsistencyException(DataConsistencyException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .subErrors(Collections.emptyList())
                        .build());
    }

    @ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleOperationNotAllowedException(OperationNotAllowedException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.FORBIDDEN.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .subErrors(Collections.emptyList())
                        .build());
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ErrorResponse> handleExternalServiceException(ExternalServiceException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.BAD_GATEWAY.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .subErrors(Collections.emptyList())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        log.error("Internal Server Error: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .subErrors(Collections.emptyList())
                        .build());
    }
}
