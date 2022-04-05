package com.muscle_tracking_api.MuscleTrackingApi.service.log;

import com.muscle_tracking_api.MuscleTrackingApi.dao.log.LogDao;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.TrainingLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    LogDao logDao;

    public List<TrainingLog> getAllLog(String userId) {
        return logDao.selectAllLog(userId);
    }

    public TrainingLog getLogById(Integer logId) {
        return logDao.selectLogById(logId);
    }

    public TrainingLog getLatestLog(String userId) {
        return logDao.selectLatestLog(userId);
    }

    public int insertLog(TrainingLog log) {
        return logDao.insertLog(log);
    }

    public int updateLog(TrainingLog log) {
        return logDao.update(log);
    }

    public int deleteLog(TrainingLog log) {
        return logDao.delete(log);
    }
}
