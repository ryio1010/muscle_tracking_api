package com.muscle_tracking_api.MuscleTrackingApi.entity;

import com.muscle_tracking_api.MuscleTrackingApi.common.entity.BaseColumnEntity;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Entity
@Getter
@Setter
@Table(name = "m_musclepart")
public class MusclePart extends BaseColumnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "m_musclepart_musclepartid_seq")
    @Column(name = "musclepartid")
    public Integer musclepartId;

    @Column(name = "musclepartname")
    public String musclepartName;
}
