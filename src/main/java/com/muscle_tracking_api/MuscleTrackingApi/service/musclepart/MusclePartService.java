package com.muscle_tracking_api.MuscleTrackingApi.service.musclepart;

import com.muscle_tracking_api.MuscleTrackingApi.dao.musclepart.MusclePartDao;
import com.muscle_tracking_api.MuscleTrackingApi.entity.musclepart.MusclePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusclePartService {

    @Autowired
    MusclePartDao musclePartDao;

    public List<MusclePart> getMusclePartAll() {
        return musclePartDao.selectAllMusclePart();
    }
}
