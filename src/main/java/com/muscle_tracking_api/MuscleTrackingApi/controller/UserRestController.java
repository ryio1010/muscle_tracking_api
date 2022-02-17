package com.muscle_tracking_api.MuscleTrackingApi.controller;

import com.muscle_tracking_api.MuscleTrackingApi.entity.user.*;
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
     * @param userLoginForm
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginForm userLoginForm) {
        // userIdからレコードを取得
        User userInfo = userService.getUserById(userLoginForm.userid);

        // ユーザー存在チェック
        if (userInfo == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        // 認証チェック
        if (!userLoginForm.userid.equals(userInfo.uid) || !userLoginForm.password.equals(userInfo.password)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        // ユーザー情報をセット
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.userid = userInfo.uid;
        userLoginResponse.username = userInfo.userNane;
        userLoginResponse.password = userInfo.password;

        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }

    /**
     * ユーザー新規登録API
     *
     * @param userRegisterForm
     * @return
     */
    @PostMapping("/register")
    ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterForm userRegisterForm) {
        // ユーザーID重複チェック
        User userinfo = userService.getUserById(userRegisterForm.userid);
        if (userinfo != null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        // 新規ユーザー登録
        User registerUserInfo = new User();
        registerUserInfo.uid = userRegisterForm.userid;
        registerUserInfo.userNane = userRegisterForm.username;
        registerUserInfo.password = userRegisterForm.password;

        userService.insert(registerUserInfo);

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.userid = registerUserInfo.uid;
        userRegisterResponse.username = registerUserInfo.userNane;
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

    @GetMapping("/error")
    public String error() {
        return "ERROR!!";
    }
}
