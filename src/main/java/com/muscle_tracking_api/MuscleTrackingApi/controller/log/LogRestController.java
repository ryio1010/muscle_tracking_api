package com.muscle_tracking_api.MuscleTrackingApi.controller.log;

import com.muscle_tracking_api.MuscleTrackingApi.entity.log.TrainingLog;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogRegisterForm;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogResponse;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.LogUpdateForm;
import com.muscle_tracking_api.MuscleTrackingApi.exception.NoDataFoundException;
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

        // 全ログデータ取得
        List<TrainingLog> allLog = logService.getAllLog(userId);
        if (allLog == null) {
            throw new NoDataFoundException("Not Found Any Log!!");
        }

        // レスポンスの作成
        List<LogResponse> responses = new ArrayList<>();
        for (TrainingLog log : allLog) {
            LogResponse logResponse = new LogResponse();
            modelMapper.map(log, logResponse);
            if (logResponse.trainingMemo == null) {
                logResponse.trainingMemo = "";
            }

            responses.add(logResponse);
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<LogResponse> addLog(@ModelAttribute LogRegisterForm logRegisterForm) {

        // ログデータInsert
        TrainingLog addLog = new TrainingLog();
        modelMapper.map(logRegisterForm, addLog);
        logService.insertLog(addLog);

        // TODO : Insertデータを取得するようにしたい
        // レスポンスの作成
        TrainingLog insertedLog = logService.getLatestLog(logRegisterForm.userId);
        LogResponse response = new LogResponse();
        modelMapper.map(insertedLog, response);
        if (response.trainingMemo == null) {
            response.trainingMemo = "";
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping()
    @ResponseBody
    ResponseEntity<LogResponse> updateLog(@ModelAttribute LogUpdateForm logUpdateForm) {

        // 更新対象を取得
        TrainingLog logInfo = logService.getLogById(Integer.valueOf(logUpdateForm.logId));
        if (logInfo == null) {
            throw new NoDataFoundException("Not Found Log Of This Id!!");
        }

        // 情報更新し、Update
        modelMapper.map(logUpdateForm, logInfo);
        logInfo.updId = logUpdateForm.userId;
        logInfo.updDate = new Timestamp(System.currentTimeMillis());
        logService.updateLog(logInfo);

        // TODO : Updateデータを取得するようにしたい
        // レスポンスの作成
        LogResponse response = new LogResponse();
        modelMapper.map(logInfo, response);
        if (response.trainingMemo == null) {
            response.trainingMemo = "";
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{logId}")
    @ResponseBody
    ResponseEntity<String> deleteLog(@PathVariable String logId) {

        TrainingLog deleteLog = logService.getLogById(Integer.valueOf(logId));

        deleteLog.logId = Integer.valueOf(logId);
        logService.deleteLog(deleteLog);
        return new ResponseEntity<>(logId, HttpStatus.OK);
    }
}
