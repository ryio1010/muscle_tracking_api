package com.muscle_tracking_api.MuscleTrackingApi.dao.log;

import com.muscle_tracking_api.MuscleTrackingApi.entity.log.TrainingLog;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface LogDao {

    @Select
    List<TrainingLog> selectAllLog(String userId);

    @Select
    TrainingLog selectLogById(Integer logId);

    @Select
    TrainingLog selectLatestLog(String userId);

    @Insert(sqlFile = true)
    int insertLog(TrainingLog log);

    @Update
    int update(TrainingLog log);

    @Delete
    int delete(TrainingLog log);
}
