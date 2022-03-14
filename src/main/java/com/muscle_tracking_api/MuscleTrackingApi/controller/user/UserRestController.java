package com.muscle_tracking_api.MuscleTrackingApi.controller.user;

import com.muscle_tracking_api.MuscleTrackingApi.entity.user.*;
import com.muscle_tracking_api.MuscleTrackingApi.service.user.UserService;
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
        User userInfo = userService.getUserById(userLoginForm.userId);

        // ユーザー存在チェック
        if (userInfo == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        // 認証チェック
        if (!userLoginForm.userId.equals(userInfo.userId) || !userLoginForm.password.equals(userInfo.password)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        // ユーザー情報をセット
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.userId = userInfo.userId;
        userLoginResponse.userName = userInfo.userNane;
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
    ResponseEntity<Boolean> register(@ModelAttribute UserRegisterForm userRegisterForm) {
        // ユーザーID重複チェック
        User userinfo = userService.getUserById(userRegisterForm.userId);
        if (userinfo != null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        // 新規ユーザー登録
        User registerUserInfo = new User();
        registerUserInfo.userId = userRegisterForm.userId;
        registerUserInfo.userNane = userRegisterForm.userName;
        registerUserInfo.password = userRegisterForm.password;
        registerUserInfo.regId = userRegisterForm.userId;
        registerUserInfo.regDate = new Timestamp(System.currentTimeMillis());
        registerUserInfo.updId = userRegisterForm.userId;
        registerUserInfo.updDate = new Timestamp(System.currentTimeMillis());
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
        User userInfo = userService.getUserById(userUpdateForm.userId);

        // m_user更新処理
        userInfo.userId = userUpdateForm.userId;
        userInfo.userNane = userUpdateForm.userName;
        userInfo.password = userUpdateForm.password;
        userInfo.updId = userUpdateForm.userId;
        userInfo.updDate = new Timestamp(System.currentTimeMillis());

        // DB登録
        userService.update(userInfo);

        // update情報セット
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        userUpdateResponse.userId = userUpdateForm.userId;
        userUpdateResponse.userName = userUpdateForm.userName;
        userUpdateResponse.password = userUpdateForm.password;

        return new ResponseEntity<>(userUpdateResponse,HttpStatus.OK);
    }


    @GetMapping("/error")
    public String error() {
        return "ERROR!!";
    }
}
