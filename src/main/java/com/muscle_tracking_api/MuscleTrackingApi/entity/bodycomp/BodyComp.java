package com.muscle_tracking_api.MuscleTrackingApi.entity.bodycomp;

import com.muscle_tracking_api.MuscleTrackingApi.common.entity.BaseColumnEntity;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Entity
@Getter
@Setter
@Table(name = "t_bodycomp")
public class BodyComp extends BaseColumnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "t_bodycomposition_bodycomid_seq")
    @Column(name = "bodycompid")
    public Integer bodyCompId;

    @Column(name = "height")
    public Double height;

    @Column(name = "weight")
    public Double weight;

    @Column(name = "bfp")
    public Double bfp;

    @Column(name = "date")
    public String bodyCompDate;

    @Column(name = "userid")
    public String userId;
}