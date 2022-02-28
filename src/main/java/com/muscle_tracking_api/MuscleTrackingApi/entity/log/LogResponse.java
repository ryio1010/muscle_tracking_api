package com.muscle_tracking_api.MuscleTrackingApi.entity.log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogResponse {
    public Integer logId;
    public String menuName;
    public Double trainingWeight;
    public Integer trainingCount;
    public String trainingDate;
}
