package com.muscle_tracking_api.MuscleTrackingApi.controller.log;

import com.muscle_tracking_api.MuscleTrackingApi.entity.log.Log;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogRegisterForm;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogResponse;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogUpdateForm;
import com.muscle_tracking_api.MuscleTrackingApi.service.log.LogService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{userId}")
    @ResponseBody
    ResponseEntity<List<LogResponse>> getALlLog(@PathVariable String userId) {

        List<Log> allLog = logService.getAllLog(userId);
        List<LogResponse> responses = new ArrayList<>();

        for (Log log : allLog) {
            LogResponse logResponse = new LogResponse();

            modelMapper.map(log, logResponse);
            responses.add(logResponse);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<Boolean> addLog(@ModelAttribute LogRegisterForm logRegisterForm) {

        Log addLog = new Log();

        modelMapper.map(logRegisterForm, addLog);
        addLog.userId = logRegisterForm.userId;

        logService.insertLog(addLog);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping
    @ResponseBody
    ResponseEntity<LogResponse> updateLog(@ModelAttribute LogUpdateForm logUpdateForm) {
        Log logInfo = logService.getLogById(Integer.valueOf(logUpdateForm.logId));

        logInfo.menuId = Integer.valueOf(logUpdateForm.menuId);
        logInfo.trainingWeight = logUpdateForm.trainingWeight;
        logInfo.trainingCount = logUpdateForm.trainingCount;
        logInfo.trainingDate = logUpdateForm.trainingDate;
        logInfo.updId = logUpdateForm.userId;
        logInfo.updDate = new Timestamp(System.currentTimeMillis());

        logService.updateLog(logInfo);

        LogResponse response = new LogResponse();
        modelMapper.map(logInfo, response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{logId}")
    @ResponseBody
    ResponseEntity<String> deleteLog(@PathVariable String logId) {
        Log deleteLog = new Log();
        deleteLog.logId = Integer.valueOf(logId);
        logService.deleteLog(deleteLog);
        return new ResponseEntity<>(logId, HttpStatus.OK);
    }
}
