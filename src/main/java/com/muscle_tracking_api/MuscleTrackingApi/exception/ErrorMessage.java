package com.muscle_tracking_api.MuscleTrackingApi.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private int status;
    private String errorMessage;

    public ErrorMessage(int status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
