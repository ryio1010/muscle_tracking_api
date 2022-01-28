package com.muscle_tracking_api.MuscleTrackingApi.controller;

import com.muscle_tracking_api.MuscleTrackingApi.entity.User;
import com.muscle_tracking_api.MuscleTrackingApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("login")
    ResponseEntity<String> login() {
        List<User> users = userService.getUserAll();
        System.out.println(users);
        return new ResponseEntity<>("LOGIN!!", HttpStatus.OK);
    }
}
