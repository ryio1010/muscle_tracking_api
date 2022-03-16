package com.muscle_tracking_api.MuscleTrackingApi.dao.user;


import com.muscle_tracking_api.MuscleTrackingApi.entity.user.User;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface UserDao {

    @Select
    User selectUserById(String userId);

    @Insert(sqlFile = true)
    int insertUser(User user);

    @Update
    int update(User user);

    @Delete
    int delete(User user);
}
