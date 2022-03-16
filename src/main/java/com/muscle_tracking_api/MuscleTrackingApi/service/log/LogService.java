package com.muscle_tracking_api.MuscleTrackingApi.service.log;

import com.muscle_tracking_api.MuscleTrackingApi.dao.log.LogDao;
import com.muscle_tracking_api.MuscleTrackingApi.entity.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    LogDao logDao;

    public List<Log> getAllLog(String userId) {
        return logDao.selectAllLog(userId);
    }

    public int insertLog(Log log) {
        return logDao.insert(log);
    }

    public int updateLog(Log log) {
        return logDao.update(log);
    }

    public int deleteLog(Log log) {
        return logDao.delete(log);
    }
}
