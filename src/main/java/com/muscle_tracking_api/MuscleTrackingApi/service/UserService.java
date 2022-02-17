package com.muscle_tracking_api.MuscleTrackingApi.service;

import com.muscle_tracking_api.MuscleTrackingApi.entity.user.User;
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

    public User getUserById(String userId) {
        return userDao.selectUserByUserName(userId);
    }

    public int insert(User user) {
        return userDao.insert(user);
    }
}
