package com.muscle_tracking_api.MuscleTrackingApi.entity.musclepart;

import com.muscle_tracking_api.MuscleTrackingApi.common.entity.BaseColumnEntity;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;

@Entity
@Getter
@Setter
@Table(name = "m_musclepart")
public class MusclePart extends BaseColumnEntity {

    @Column(name = "musclepartid")
    public String musclePartId;

    @Column(name = "musclepartname")
    public String musclePartName;
}
