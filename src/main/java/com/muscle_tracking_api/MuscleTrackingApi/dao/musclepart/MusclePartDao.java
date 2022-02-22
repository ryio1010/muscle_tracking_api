package com.muscle_tracking_api.MuscleTrackingApi.dao.musclepart;

import com.muscle_tracking_api.MuscleTrackingApi.entity.musclepart.MusclePart;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface MusclePartDao {

    @Select
    List<MusclePart> selectAllMusclePart();
}
