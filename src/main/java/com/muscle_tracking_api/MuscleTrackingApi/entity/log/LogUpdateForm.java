package com.muscle_tracking_api.MuscleTrackingApi.entity.log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogUpdateForm {
    public String logId;
    public String menuId;
    public String menuName;
    public Double trainingWeight;
    public Integer trainingCount;
    public String trainingDate;
    public String userId;
}
