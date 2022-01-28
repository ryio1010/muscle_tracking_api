package com.muscle_tracking_api.MuscleTrackingApi.service;

import com.muscle_tracking_api.MuscleTrackingApi.entity.User;
import com.muscle_tracking_api.MuscleTrackingApi.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getUserAll() {
        return userDao.selectAll();
    }
}
