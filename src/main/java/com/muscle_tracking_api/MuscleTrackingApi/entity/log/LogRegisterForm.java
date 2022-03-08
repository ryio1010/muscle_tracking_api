package com.muscle_tracking_api.MuscleTrackingApi.entity.log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogRegisterForm {
    public String menuId;
    public String menuName;
    public String trainingWeight;
    public String trainingCount;
    public String trainingDate;
    public String userId;
}
