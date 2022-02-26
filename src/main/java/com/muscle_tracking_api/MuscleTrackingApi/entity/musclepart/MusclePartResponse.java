package com.muscle_tracking_api.MuscleTrackingApi.entity.musclepart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusclePartResponse {
    public String musclePartId;
    public String musclePartName;

    public MusclePartResponse(String musclePartId, String musclePartName) {
        this.musclePartId = musclePartId;
        this.musclePartName = musclePartName;
    }
}
