package com.muscle_tracking_api.MuscleTrackingApi.entity.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateForm {

    public String userid;

    public String username;

    public String password;

    public Double height;

    public Double weight;
}