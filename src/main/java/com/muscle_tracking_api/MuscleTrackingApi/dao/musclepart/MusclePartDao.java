package com.muscle_tracking_api.MuscleTrackingApi.dao.musclepart;

import com.muscle_tracking_api.MuscleTrackingApi.entity.musclepart.MusclePart;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface MusclePartDao {

    @Select
    List<MusclePart> selectAllMusclePart();

    @Insert
    int insert(MusclePart musclePart);

    @Update
    int update(MusclePart musclePart);

    @Delete
    int delete(MusclePart musclePart);
}
