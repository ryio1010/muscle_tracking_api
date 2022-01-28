package com.muscle_tracking_api.MuscleTrackingApi.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity
@Getter
@Setter
public class BaseColumnEntity {

    @Column(name = "regid")
    public String regid;

    @Column(name = "regdate")
    public String regdate;

    @Column(name = "updid")
    public String updid;

    @Column(name = "upddate")
    public String upddate;

    @Column(name = "version")
    public String version;
}
