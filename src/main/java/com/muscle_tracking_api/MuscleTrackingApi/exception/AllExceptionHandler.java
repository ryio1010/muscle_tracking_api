package com.muscle_tracking_api.MuscleTrackingApi.exception;

import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoDataFoundException.class})
    public ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException ex, WebRequest request) {
        ErrorMessage msg = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return handleExceptionInternal(ex, msg, null, HttpStatus.NOT_FOUND, request);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({NotAuthenticationUserException.class})
    public ResponseEntity<Object> handleNotAuthenticationUserException(NotAuthenticationUserException ex, WebRequest request) {
        ErrorMessage msg = new ErrorMessage(HttpStatus.FORBIDDEN.value(), ex.getMessage());

        return handleExceptionInternal(ex, msg, null, HttpStatus.FORBIDDEN, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage msg = new ErrorMessage(status.value(), ex.getMessage());
        ex.printStackTrace();
        return super.handleExceptionInternal(ex, msg, headers, status, request);
    }

}
