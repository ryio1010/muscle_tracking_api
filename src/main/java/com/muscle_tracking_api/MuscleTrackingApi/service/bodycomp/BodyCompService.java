package com.muscle_tracking_api.MuscleTrackingApi.service.bodycomp;

import com.muscle_tracking_api.MuscleTrackingApi.dao.bodycomp.BodyCompDao;
import com.muscle_tracking_api.MuscleTrackingApi.entity.bodycomp.BodyComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyCompService {

    @Autowired
    BodyCompDao bodyCompDao;

    public List<BodyComp> getAllBodyComp(String userId) {
        return bodyCompDao.selectAllBodyComp(userId);
    }

    public BodyComp getLatestBodyComp(String userId) {
        return bodyCompDao.selectLatestBodyComp(userId);
    }

    public BodyComp getBodyCompById(Integer bodyCompId) {
        return bodyCompDao.selectBodyCompById(bodyCompId);
    }

    public int insertBodyComp(BodyComp bodyComp) {
        return bodyCompDao.insert(bodyComp);
    }

    public int updateBodyComp(BodyComp bodyComp) {
        return bodyCompDao.update(bodyComp);
    }
}
