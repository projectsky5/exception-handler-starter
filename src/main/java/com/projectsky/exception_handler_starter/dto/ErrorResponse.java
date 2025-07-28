package com.projectsky.exception_handler_starter.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp,
        String path,
        List<SubError> subErrors
) {
}
