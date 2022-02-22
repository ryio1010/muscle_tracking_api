package com.muscle_tracking_api.MuscleTrackingApi.service.user;

import com.muscle_tracking_api.MuscleTrackingApi.entity.user.User;
import com.muscle_tracking_api.MuscleTrackingApi.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    /**
     * m_userテーブルからuidで１件検索
     * @param userId
     * @return
     */
    public User getUserById(String userId) {
        return userDao.selectUserById(userId);
    }

    /**
     * m_userテーブルInsert
     * @param user
     * @return
     */
    public int insert(User user) {
        return userDao.insert(user);
    }

    /**
     * m_userテーブルUpdate
     * @param user
     * @return
     */
    public int update(User user) {
        return userDao.update(user);
    }
}
