package com.muscle_tracking_api.MuscleTrackingApi.controller.musclepart;

import com.muscle_tracking_api.MuscleTrackingApi.entity.musclepart.MusclePart;
import com.muscle_tracking_api.MuscleTrackingApi.entity.musclepart.MusclePartResponse;
import com.muscle_tracking_api.MuscleTrackingApi.exception.NoDataFoundException;
import com.muscle_tracking_api.MuscleTrackingApi.service.musclepart.MusclePartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/musclepart")
public class MusclePartRestController {

    @Autowired
    MusclePartService musclePartService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    ResponseEntity<List<MusclePartResponse>> getAllMusclePart() {

        // 全データ取得
        List<MusclePart> allMusclePart = musclePartService.getMusclePartAll();
        if (allMusclePart == null) {
            throw new NoDataFoundException("Not Found Muscle Part!!");
        }

        // レスポンス作成
        List<MusclePartResponse> musclePartResponses = new ArrayList<>();
        for (MusclePart musclePart : allMusclePart) {
            MusclePartResponse response = new MusclePartResponse();
            modelMapper.map(musclePart, response);
            musclePartResponses.add(response);
        }

        return new ResponseEntity<>(musclePartResponses, HttpStatus.OK);
    }
}
