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

    public Menu getLatestMenu(String userId) {
        return menuDao.selectLatestMenu(userId);
    }

    public int insertMenu(Menu menu) {
        return menuDao.insertMenu(menu);
    }

    public int updateMenu(Menu menu) {
        return menuDao.update(menu);
    }

    public int deleteMenu(Menu menu) {
        return menuDao.delete(menu);
    }
}
