package com.muscle_tracking_api.MuscleTrackingApi.controller.user;

import com.muscle_tracking_api.MuscleTrackingApi.entity.user.*;
import com.muscle_tracking_api.MuscleTrackingApi.service.user.UserService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

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

        // response
        UserLoginResponse userLoginResponse = new UserLoginResponse();

        // ユーザー存在チェック
        if (userInfo == null) {
            return new ResponseEntity<>(userLoginResponse, HttpStatus.NO_CONTENT);
        }

        // 認証チェック
        if (!userLoginForm.password.equals(userInfo.password)) {
            return new ResponseEntity<>(userLoginResponse, HttpStatus.FORBIDDEN);
        }

        // ユーザー情報をセット
        modelMapper.map(userInfo, userLoginResponse);

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

        // 新規ユーザー登録情報をセット
        User registerUserInfo = new User();
        modelMapper.map(userRegisterForm, registerUserInfo);

        // ユーザー登録
        userService.insertUser(registerUserInfo);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * ユーザー情報更新API
     *
     * @param userUpdateForm
     * @return
     */
    @PutMapping
    ResponseEntity<UserUpdateResponse> update(@ModelAttribute UserUpdateForm userUpdateForm) {
        // useridで検索
        User userInfo = userService.getUserById(userUpdateForm.userId);

        // m_user更新処理
        userInfo.userId = userUpdateForm.userId;
        userInfo.userName = userUpdateForm.userName;
        userInfo.password = userUpdateForm.password;
        userInfo.updId = userUpdateForm.userId;
        userInfo.updDate = new Timestamp(System.currentTimeMillis());

        // DB登録
        userService.update(userInfo);

        // update情報セット
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        modelMapper.map(userUpdateForm, userUpdateResponse);
//        userUpdateResponse.userId = userUpdateForm.userId;
//        userUpdateResponse.userName = userUpdateForm.userName;
//        userUpdateResponse.password = userUpdateForm.password;

        return new ResponseEntity<>(userUpdateResponse, HttpStatus.OK);
    }


    @GetMapping("/error")
    public String error() {
        return "ERROR!!";
    }
}
