package com.muscle_tracking_api.MuscleTrackingApi.dao.log;

import com.muscle_tracking_api.MuscleTrackingApi.entity.log.Log;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface LogDao {

    @Select
    List<Log> selectAllLog(String userId);

    @Insert(sqlFile = true)
    int insertLog(Log log);

    @Update
    int update(Log log);

    @Delete
    int delete(Log log);
}
