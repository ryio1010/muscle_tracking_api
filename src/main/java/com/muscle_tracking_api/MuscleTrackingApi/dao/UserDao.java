package com.muscle_tracking_api.MuscleTrackingApi.dao;


import com.muscle_tracking_api.MuscleTrackingApi.entity.User;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface UserDao {
    @Select
    List<User> selectAll();
}
