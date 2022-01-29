package com.muscle_tracking_api.MuscleTrackingApi.controller;

import com.muscle_tracking_api.MuscleTrackingApi.entity.User;
import com.muscle_tracking_api.MuscleTrackingApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/login/{userName}/{password}")
    ResponseEntity<String> login(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        // userNameからレコードを取得
        User user = userService.getUserByUserName(userName);

        if (user != null) {
            if (user.password.equals(password)) {
                return new ResponseEntity<>("you can Login!!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("you are not able to login...", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>("no user FOUND!!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/register/{userName}")
    ResponseEntity<String> registerUser(@PathVariable("userName") String userName) {

        // userName重複チェック
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            // DB登録
            User newUser = new User();
            Timestamp date = new Timestamp(new Date().getTime());
            newUser.userNane = userName;
            newUser.password = userName;
            newUser.regid = userName;
            newUser.regdate = date;
            newUser.updid = userName;
            newUser.upddate = date;
            int result = userService.insert(newUser);
            return new ResponseEntity<>("REGISTERD!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT REGISTERD!!", HttpStatus.OK);
    }

    @GetMapping("/error")
    public String error() {
        return "ERROR!!";
    }
}
