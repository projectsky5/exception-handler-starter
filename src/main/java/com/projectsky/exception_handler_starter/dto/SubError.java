package com.projectsky.exception_handler_starter.dto;

import lombok.Builder;

@Builder
public record SubError(
        String object,
        String field,
        Object rejectedValue,
        String message
) {
}
