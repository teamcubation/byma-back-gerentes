package com.byma.crudgerente.exception_handler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorMessageResponse {
    private final String exception;
    private final int status;
    private final String message;
    private final String path;
    private final String method;
    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();
}
