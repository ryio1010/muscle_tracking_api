package com.muscle_tracking_api.MuscleTrackingApi.dao.menu;

import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.Menu;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface MenuDao {
    @Select
    List<Menu> selectAllMenus();

    @Select
    List<Menu> selectMenusByMusclePart(String musclePart);

    @Insert
    int insert(Menu menu);

    @Update
    int update(Menu menu);

    @Delete
    int delete(Menu menu);
}
