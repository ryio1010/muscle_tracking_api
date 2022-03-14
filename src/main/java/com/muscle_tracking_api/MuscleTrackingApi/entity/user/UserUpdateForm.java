package com.muscle_tracking_api.MuscleTrackingApi.entity.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateForm {
    public String userId;
    public String userName;
    public String password;
}
