package com.muscle_tracking_api.MuscleTrackingApi.controller.log;

import com.muscle_tracking_api.MuscleTrackingApi.entity.log.Log;
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

}
