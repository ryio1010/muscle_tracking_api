package com.muscle_tracking_api.MuscleTrackingApi.controller.log;

import com.muscle_tracking_api.MuscleTrackingApi.entity.log.Log;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogRegisterForm;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogResponse;
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
}
