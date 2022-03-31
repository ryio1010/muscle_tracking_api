package com.muscle_tracking_api.MuscleTrackingApi.dao.bodycomp;

import com.muscle_tracking_api.MuscleTrackingApi.entity.bodycomp.BodyComp;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface BodyCompDao {

    @Select
    List<BodyComp> selectAllBodyComp(String userId);

    @Select
    BodyComp selectLatestBodyComp(String userId);

    @Select
    BodyComp selectBodyCompById(Integer bodyCompId);

    @Insert(sqlFile = true)
    int insertBodyComp(BodyComp bodyComp);

    @Update
    int update(BodyComp bodyComp);

    @Delete
    int delete(BodyComp bodyComp);
}
