package com.muscle_tracking_api.MuscleTrackingApi.controller.musclepart;

import com.muscle_tracking_api.MuscleTrackingApi.entity.musclepart.MusclePart;
import com.muscle_tracking_api.MuscleTrackingApi.service.musclepart.MusclePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/musclepart")
public class MusclePartRestController {

    @Autowired
    MusclePartService musclePartService;

    @GetMapping
    @ResponseBody
    ResponseEntity<List<MusclePart>> getAllMusclePart() {
        List<MusclePart> allMusclePart = musclePartService.getMusclePartAll();
        return new ResponseEntity<>(allMusclePart, HttpStatus.OK);
    }
}