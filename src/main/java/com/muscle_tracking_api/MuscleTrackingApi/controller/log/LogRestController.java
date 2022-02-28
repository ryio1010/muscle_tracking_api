package com.muscle_tracking_api.MuscleTrackingApi.controller.log;

import com.muscle_tracking_api.MuscleTrackingApi.entity.log.Log;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogResponse;
import com.muscle_tracking_api.MuscleTrackingApi.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/log")
public class LogRestController {

    @Autowired
    LogService logService;

    @GetMapping("/{userId}")
    @ResponseBody
    ResponseEntity<List<LogResponse>> getALlLog(@PathVariable String userId) {
        List<Log> allLog = logService.getAllLog(userId);
        List<LogResponse> responses = new ArrayList<>();
        for (Log log: allLog) {
            LogResponse logResponse = new LogResponse();
            logResponse.logId = log.id;
            logResponse.menuName = log.menuName;
            logResponse.trainingWeight = Double.valueOf(log.trainingWeight);
            logResponse.trainingCount = log.trainingCount;
            logResponse.trainingDate = log.trainingDate;
            responses.add(logResponse);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

}
