package com.muscle_tracking_api.MuscleTrackingApi.dao.user;


import com.muscle_tracking_api.MuscleTrackingApi.entity.user.User;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface UserDao {

    @Select
    User selectUserById(String userId);

    @Insert
    int insert(User user);

    @Update
    int update(User user);
}
