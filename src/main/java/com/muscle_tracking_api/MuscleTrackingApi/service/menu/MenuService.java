package com.muscle_tracking_api.MuscleTrackingApi.service.menu;

import com.muscle_tracking_api.MuscleTrackingApi.dao.menu.MenuDao;
import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuDao menuDao;

    public List<Menu> getMenuAll() {
        return menuDao.selectAllMenus();
    }

    public List<Menu> getMenuByMusclePart(String musclePart) {
        return menuDao.selectMenusByMusclePart(musclePart);
    }

    public int insertMenu(Menu menu) {
        return menuDao.insert(menu);
    }
}