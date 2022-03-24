package com.muscle_tracking_api.MuscleTrackingApi.controller.menu;

import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.Menu;
import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.MenuRegisterForm;
import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.MenuResponse;
import com.muscle_tracking_api.MuscleTrackingApi.service.menu.MenuService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuRestController {

    @Autowired
    MenuService menuService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<List<MenuResponse>> addMenu(@ModelAttribute MenuRegisterForm menuRegisterForm) {
        // menu登録処理
        Menu registerMenu = new Menu();
        modelMapper.map(menuRegisterForm, registerMenu);

        menuService.insertMenu(registerMenu);

        List<Menu> allMenu = menuService.getMenuAll();
        List<MenuResponse> menuResponses = new ArrayList<>();
        for (Menu menu : allMenu) {
            MenuResponse response = new MenuResponse();
            modelMapper.map(menu, response);
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
        for (Menu menu : allMenu) {
            MenuResponse response = new MenuResponse();
            modelMapper.map(menu, response);
            menuResponses.add(response);
        }
        return new ResponseEntity<>(menuResponses, HttpStatus.OK);
    }
}
