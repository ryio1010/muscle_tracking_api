package com.muscle_tracking_api.MuscleTrackingApi.exception;

public class NotAuthenticationUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotAuthenticationUserException(String errorMessage) {
        super(errorMessage);
    }
}
