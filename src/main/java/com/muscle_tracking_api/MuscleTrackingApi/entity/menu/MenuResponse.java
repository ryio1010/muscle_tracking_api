package com.muscle_tracking_api.MuscleTrackingApi.entity.menu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuResponse {
    public Integer menuId;
    public String menuName;
    public String musclePart;
}
