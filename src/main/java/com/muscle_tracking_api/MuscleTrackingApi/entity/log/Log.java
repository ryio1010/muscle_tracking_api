package com.muscle_tracking_api.MuscleTrackingApi.entity.log;

import com.muscle_tracking_api.MuscleTrackingApi.common.entity.BaseColumnEntity;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Entity
@Getter
@Setter
@Table(name = "t_traininglog")
public class Log extends BaseColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_traininglog_id_seq")
    @Column(name = "id")
    public Integer id;

    @Column(name = "menuid")
    public Integer menuId;

    @Column(name = "trainingweight")
    public Integer trainingWeight;

    @Column(name = "trainingcount")
    public Integer trainingCount;

    @Column(name = "trainingdate")
    public String trainingDate;

    @Column(name = "userid")
    public String userId;
}
