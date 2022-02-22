package com.muscle_tracking_api.MuscleTrackingApi.controller.menu;

import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.Menu;
import com.muscle_tracking_api.MuscleTrackingApi.entity.menu.MenuRegisterForm;
import com.muscle_tracking_api.MuscleTrackingApi.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuRestController {

    @Autowired
    MenuService menuService;

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<Boolean> addMenu(@ModelAttribute MenuRegisterForm menuRegisterForm) {
        // menu登録処理
        Menu registerMenu = new Menu();
        registerMenu.menuName = menuRegisterForm.menuName;
        registerMenu.musclepartId = menuRegisterForm.musclePartId;
        menuService.insertMenu(registerMenu);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    ResponseEntity<List<Menu>> getAllMenu() {
        // 全メニュー取得処理
        List<Menu> allMenu = menuService.getMenuAll();
        return new ResponseEntity<>(allMenu,HttpStatus.OK);
    }

    @GetMapping("/{musclePart}")
    @ResponseBody
    ResponseEntity<List<Menu>> getMenuByMusclePart(@ModelAttribute String musclePart) {
        // 部位別メニュー取得処理
        List<Menu> menuOfMusclePart = menuService.getMenuByMusclePart(musclePart);
        return new ResponseEntity<>(menuOfMusclePart,HttpStatus.OK);
    }
}
