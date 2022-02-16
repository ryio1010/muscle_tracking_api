package com.muscle_tracking_api.MuscleTrackingApi.controller;

import com.muscle_tracking_api.MuscleTrackingApi.entity.User;
import com.muscle_tracking_api.MuscleTrackingApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    UserService userService;

    /**
     * ユーザーログインAPI
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    ResponseEntity<User> login(@RequestBody User user) {
        // userIdからレコードを取得
        User userInfo = userService.getUserById(user.uid);

        // ユーザー存在チェック
        if (userInfo == null) {
            return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
        }

        // 認証チェック
        if (!user.uid.equals(userInfo.uid) || !user.password.equals(userInfo.password)) {
            return new ResponseEntity<>(user, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    /**
     * ユーザー新規登録API
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    ResponseEntity<Boolean> register(@RequestBody User user) {
        // ユーザーID重複チェック
        User userinfo = userService.getUserById(user.uid);
        if (userinfo == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        // 新規ユーザー登録
        userService.insert(user);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/error")
    public String error() {
        return "ERROR!!";
    }
}
