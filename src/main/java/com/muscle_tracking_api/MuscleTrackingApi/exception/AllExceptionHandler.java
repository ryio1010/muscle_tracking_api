package com.muscle_tracking_api.MuscleTrackingApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AllExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoDataFoundException.class})
    public ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException ex, WebRequest request) {
        ErrorMessage msg = new ErrorMessage();
        msg.setErrorMessage(msg.getErrorMessage());

        return null;
    }

    private ResponseEntity<Object> handleExceptionInternal(NoDataFoundException body, HttpStatus status) {
        return null;
    }

}
