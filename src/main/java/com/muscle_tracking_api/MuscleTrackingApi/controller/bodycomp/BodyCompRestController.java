package com.muscle_tracking_api.MuscleTrackingApi.controller.bodycomp;

import com.muscle_tracking_api.MuscleTrackingApi.entity.bodycomp.BodyComp;
import com.muscle_tracking_api.MuscleTrackingApi.entity.bodycomp.BodyCompRegisterForm;
import com.muscle_tracking_api.MuscleTrackingApi.entity.bodycomp.BodyCompResponse;
import com.muscle_tracking_api.MuscleTrackingApi.entity.bodycomp.BodyCompUpdateForm;
import com.muscle_tracking_api.MuscleTrackingApi.service.bodycomp.BodyCompService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bodycomp")
public class BodyCompRestController {

    @Autowired
    BodyCompService bodyCompService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{userId}")
    @ResponseBody
    ResponseEntity<List<BodyCompResponse>> getAllBodyComp(@PathVariable String userId) {

        // 全体組成データ取得
        List<BodyComp> allBodyComp = bodyCompService.getAllBodyComp(userId);
        List<BodyCompResponse> responses = new ArrayList<>();

        // レスポンスの作成
        for (BodyComp bc : allBodyComp) {
            BodyCompResponse bodyCompResponse = new BodyCompResponse();
            modelMapper.map(bc, bodyCompResponse);
            bodyCompResponse.bmi = calculateBmi(bc.height, bc.weight);
            bodyCompResponse.lbm = calculateLbm(bc.weight, bc.bfp);

            responses.add(bodyCompResponse);
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<BodyCompResponse> addBodyComp(@ModelAttribute BodyCompRegisterForm bodyCompRegisterForm) {

        // 体組成データInsert
        BodyComp addBodyComp = new BodyComp();
        modelMapper.map(bodyCompRegisterForm, addBodyComp);
        bodyCompService.insertBodyComp(addBodyComp);

        // レスポンス作成
        BodyComp latestBodyComp = bodyCompService.getLatestBodyComp(bodyCompRegisterForm.userId);
        BodyCompResponse response = new BodyCompResponse();

        modelMapper.map(latestBodyComp, response);
        response.bmi = calculateBmi(response.height, response.weight);
        response.lbm = calculateLbm(response.weight, response.bfp);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{bodyCompId}")
    @ResponseBody
    ResponseEntity<BodyCompResponse> updateBodyComp(@ModelAttribute BodyCompUpdateForm bodyCompUpdateForm) {

        // 更新対象を取得し、詰替え
        BodyComp updateBodyComp = bodyCompService.getBodyCompById(bodyCompUpdateForm.getBodyCompId());
        modelMapper.map(bodyCompUpdateForm, updateBodyComp);

        // 更新ID,更新日の更新
        updateBodyComp.updId = bodyCompUpdateForm.userId;
        updateBodyComp.updDate = new Timestamp(System.currentTimeMillis());

        bodyCompService.updateBodyComp(updateBodyComp);

        // レスポンスの作成
        BodyComp bc = bodyCompService.getBodyCompById(bodyCompUpdateForm.bodyCompId);
        BodyCompResponse response = new BodyCompResponse();

        modelMapper.map(bc, response);
        response.bmi = calculateBmi(response.height, response.weight);
        response.lbm = calculateLbm(response.weight, response.bfp);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private double calculateBmi(Double height, Double weight) {
        return Math.round(weight / ((height / 100) * (height / 100)) * 100.0) / 100.0;
    }

    private double calculateLbm(Double weight, Double bfp) {
        return Math.round(weight * (100 - bfp) / 100 * 100.0) / 100.0;
    }
}
