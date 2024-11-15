package com.byma.crudgerente.exception_handler;

import com.byma.crudgerente.application.exception.GerenteNoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ErrorMessageResponse handleException(Exception exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GerenteNoEncontradoException.class)
    public ErrorMessageResponse handleGerenteNoEncontrado(GerenteNoEncontradoException exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.NOT_FOUND);
    }

    private ErrorMessageResponse createErrorMessageResponse(Exception exception, HttpServletRequest request, HttpStatus status) {
        return ErrorMessageResponse.builder()
                .exception(exception.getClass().getName())
                .message(exception.getMessage())
                .status(status.value())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build();
    }
}
