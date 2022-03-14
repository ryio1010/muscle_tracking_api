package com.muscle_tracking_api.MuscleTrackingApi.controller.log;

import com.muscle_tracking_api.MuscleTrackingApi.entity.log.Log;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogRegisterForm;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogResponse;
import com.muscle_tracking_api.MuscleTrackingApi.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
            logResponse.logId = log.logId;
            logResponse.menuId = log.menuId;
            logResponse.menuName = log.menuName;
            logResponse.trainingWeight = Double.valueOf(log.trainingWeight);
            logResponse.trainingCount = log.trainingCount;
            logResponse.trainingDate = log.trainingDate;
            responses.add(logResponse);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<Boolean> addLog(@ModelAttribute LogRegisterForm logRegisterForm) {
        Log addLog = new Log();
        addLog.menuId = Integer.valueOf(logRegisterForm.menuId);
        addLog.menuName = logRegisterForm.menuName;
        addLog.trainingWeight = Integer.valueOf(logRegisterForm.trainingWeight);
        addLog.trainingCount = Integer.valueOf(logRegisterForm.trainingCount);
        addLog.trainingDate = logRegisterForm.trainingDate;
        addLog.userId = logRegisterForm.userId;
        addLog.regId = logRegisterForm.userId;
        addLog.regDate = new Timestamp(System.currentTimeMillis());
        addLog.updId = logRegisterForm.userId;
        addLog.updDate = new Timestamp(System.currentTimeMillis());
        logService.insertLog(addLog);

        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
