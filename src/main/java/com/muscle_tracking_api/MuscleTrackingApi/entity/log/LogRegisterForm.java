package com.muscle_tracking_api.MuscleTrackingApi.entity.log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogRegisterForm {
    public Integer  menuId;
    public Integer trainingWeight;
    public Integer trainingCount;
    public String trainingDate;
    public String userId;
}
