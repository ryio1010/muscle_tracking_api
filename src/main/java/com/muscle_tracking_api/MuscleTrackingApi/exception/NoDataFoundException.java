package com.muscle_tracking_api.MuscleTrackingApi.exception;

public class NoDataFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoDataFoundException(String errorMessage) {
        super(errorMessage);
    }
}
