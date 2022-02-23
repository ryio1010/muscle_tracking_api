package com.muscle_tracking_api.MuscleTrackingApi.controller.log;

import com.muscle_tracking_api.MuscleTrackingApi.entity.log.Log;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogRegisterForm;
import com.muscle_tracking_api.MuscleTrackingApi.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log")
public class LogRestController {

    @Autowired
    LogService logService;

    @GetMapping("/{userId}")
    @ResponseBody
    ResponseEntity<List<Log>> getALlLog(@PathVariable String userId) {
        List<Log> allLog = logService.getAllLog(userId);
        return new ResponseEntity<>(allLog, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<Boolean> addLog(@ModelAttribute LogRegisterForm logRegisterForm) {
        // log登録処理
        Log registerLog = new Log();
        registerLog.menuId = logRegisterForm.menuId;
        registerLog.trainingWeight = logRegisterForm.trainingWeight;
        registerLog.trainingCount = logRegisterForm.trainingCount;
        registerLog.trainingDate = logRegisterForm.trainingDate;
        logService.insertLog(registerLog);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
