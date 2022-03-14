package com.muscle_tracking_api.MuscleTrackingApi.controller.menu;

import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.Menu;
import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.MenuRegisterForm;
import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.MenuResponse;
import com.muscle_tracking_api.MuscleTrackingApi.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuRestController {

    @Autowired
    MenuService menuService;

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<List<MenuResponse>> addMenu(@ModelAttribute MenuRegisterForm menuRegisterForm) {
        // menu登録処理
        Menu registerMenu = new Menu();
        registerMenu.menuName = menuRegisterForm.menuName;
        registerMenu.musclePartId = menuRegisterForm.musclePartId;
        registerMenu.userId = menuRegisterForm.userId;
        registerMenu.regId = menuRegisterForm.userId;
        registerMenu.regDate = new Timestamp(System.currentTimeMillis());
        registerMenu.updId = menuRegisterForm.userId;
        registerMenu.updDate = new Timestamp(System.currentTimeMillis());

        menuService.insertMenu(registerMenu);

        List<Menu> allMenu = menuService.getMenuAll();
        List<MenuResponse> menuResponses = new ArrayList<>();
        for ( Menu menu:allMenu) {
            MenuResponse response = new MenuResponse(menu.menuId,menu.menuName,menu.musclePartName);
            menuResponses.add(response);
        }


        return new ResponseEntity<>(menuResponses, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @ResponseBody
    ResponseEntity<List<MenuResponse>> getAllMenu(@ModelAttribute String userId) {
        // 全メニュー取得処理
        List<Menu> allMenu = menuService.getMenuAll();
        List<MenuResponse> menuResponses = new ArrayList<>();
        for ( Menu menu:allMenu) {
            MenuResponse response = new MenuResponse(menu.menuId,menu.menuName,menu.musclePartName);
            menuResponses.add(response);
        }
        return new ResponseEntity<>(menuResponses,HttpStatus.OK);
    }
}
