package com.muscle_tracking_api.MuscleTrackingApi.entity.log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogRegisterForm {
    public String menuId;
    public Double trainingWeight;
    public Integer trainingCount;
    public String trainingDate;
    public String trainingMemo;
    public String userId;
}
