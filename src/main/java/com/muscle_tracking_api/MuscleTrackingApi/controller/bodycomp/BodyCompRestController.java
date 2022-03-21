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
        List<BodyComp> allBodyComp = bodyCompService.getAllBodyComp(userId);
        List<BodyCompResponse> responses = new ArrayList<>();
        for (BodyComp bc : allBodyComp) {
            BodyCompResponse bodyCompResponse = new BodyCompResponse();
            bodyCompResponse.bodyCompId = bc.bodyCompId;
            bodyCompResponse.height = bc.height;
            bodyCompResponse.weight = bc.weight;
            bodyCompResponse.bfp = bc.bfp;
            bodyCompResponse.bmi = calculateBmi(bc.height, bc.weight);
            bodyCompResponse.lbm = calculateLbm(bc.weight, bc.bfp);
            bodyCompResponse.bodyCompDate = bc.bodyCompDate;
            responses.add(bodyCompResponse);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<BodyCompResponse> addBodyComp(@ModelAttribute BodyCompRegisterForm bodyCompRegisterForm) {
        BodyComp addBodyComp = new BodyComp();
        modelMapper.map(bodyCompRegisterForm, addBodyComp);
        addBodyComp.regId = bodyCompRegisterForm.userId;
        addBodyComp.regDate = new Timestamp(System.currentTimeMillis());
        addBodyComp.updId = bodyCompRegisterForm.userId;
        addBodyComp.updDate = new Timestamp(System.currentTimeMillis());
        bodyCompService.insertBodyComp(addBodyComp);

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

        BodyComp updateBodyComp = bodyCompService.getBodyCompById(bodyCompUpdateForm.getBodyCompId());
        modelMapper.map(bodyCompUpdateForm, updateBodyComp);
        updateBodyComp.updId = bodyCompUpdateForm.userId;
        updateBodyComp.updDate = new Timestamp(System.currentTimeMillis());
        bodyCompService.updateBodyComp(updateBodyComp);

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
