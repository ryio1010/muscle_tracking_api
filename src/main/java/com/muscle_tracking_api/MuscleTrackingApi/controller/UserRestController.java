package com.muscle_tracking_api.MuscleTrackingApi.controller;

import com.muscle_tracking_api.MuscleTrackingApi.entity.user.*;
import com.muscle_tracking_api.MuscleTrackingApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

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
    @PostMapping(value = "/login")
    @ResponseBody
    ResponseEntity<UserLoginResponse> login(@ModelAttribute UserLoginForm userLoginForm) {
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
        if (userInfo.height == null && userInfo.weight == null) {
            userLoginResponse.isFirstLogin = true;
        }else {
            userLoginResponse.isFirstLogin = false;
        }

        if (userInfo.height == null) {
            userInfo.height = Double.valueOf(0);
        }
        if (userInfo.weight == null) {
            userInfo.weight = Double.valueOf(0);
        }
        userLoginResponse.height = userInfo.height;
        userLoginResponse.weight = userInfo.weight;

        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }

    /**
     * ユーザー新規登録API
     *
     * @param userRegisterForm
     * @return
     */
    @PostMapping("/register")
    ResponseEntity<Boolean> register(@ModelAttribute UserRegisterForm userRegisterForm) {
        // ユーザーID重複チェック
        User userinfo = userService.getUserById(userRegisterForm.userid);
        if (userinfo != null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        // 新規ユーザー登録
        User registerUserInfo = new User();
        registerUserInfo.uid = userRegisterForm.userid;
        registerUserInfo.userNane = userRegisterForm.username;
        registerUserInfo.password = userRegisterForm.password;
        registerUserInfo.regid = userRegisterForm.userid;
        registerUserInfo.regdate = new Timestamp(System.currentTimeMillis());
        registerUserInfo.updid = userRegisterForm.userid;
        registerUserInfo.upddate = new Timestamp(System.currentTimeMillis());
        registerUserInfo.version = 1;


        // DB登録
        userService.insert(registerUserInfo);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * ユーザー情報更新API
     * @param userUpdateForm
     * @return
     */
    @PutMapping
    ResponseEntity<UserUpdateResponse> update(@ModelAttribute UserUpdateForm userUpdateForm) {
        // useridで検索
        User userInfo = userService.getUserById(userUpdateForm.userid);

        // m_user更新処理
        userInfo.uid = userUpdateForm.userid;
        userInfo.userNane = userUpdateForm.username;
        userInfo.password = userUpdateForm.password;
        userInfo.height = userUpdateForm.height;
        userInfo.weight = userUpdateForm.weight;
        userInfo.updid = userUpdateForm.userid;
        userInfo.upddate = new Timestamp(System.currentTimeMillis());

        // DB登録
        userService.update(userInfo);

        // update情報セット
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        userUpdateResponse.userid = userUpdateForm.userid;
        userUpdateResponse.username = userUpdateForm.username;
        userUpdateResponse.password = userUpdateForm.password;
        userUpdateResponse.height = userUpdateForm.height;
        userUpdateResponse.weight = userUpdateForm.weight;

        return new ResponseEntity<>(userUpdateResponse,HttpStatus.OK);
    }


    @GetMapping("/error")
    public String error() {
        return "ERROR!!";
    }
}
