package com.muscle_tracking_api.MuscleTrackingApi.entity.log;

import com.muscle_tracking_api.MuscleTrackingApi.common.entity.BaseColumnEntity;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Entity
@Getter
@Setter
@Table(name = "t_traininglog")
public class TrainingLog extends BaseColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_traininglog_logid_seq")
    @Column(name = "logid")
    public Integer logId;

    @Column(name = "menuid")
    public Integer menuId;

    @Column(name = "menuname", insertable = false, updatable = false)
    public String menuName;

    @Column(name = "musclepartname", insertable = false, updatable = false)
    public String musclePart;

    @Column(name = "trainingweight")
    public Double trainingWeight;

    @Column(name = "trainingcount")
    public Integer trainingCount;

    @Column(name = "trainingdate")
    public String trainingDate;

    @Column(name = "trainingmemo")
    public String trainingMemo;

    @Column(name = "userid")
    public String userId;
}
