package com.muscle_tracking_api.MuscleTrackingApi.entity.bodycomp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyCompUpdateForm {
    public Integer bodyCompId;
    public Double height;
    public Double weight;
    public Double bfp;
    public String userId;
}
