package com.muscle_tracking_api.MuscleTrackingApi.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Version;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class BaseColumnEntity {

    @Column(name = "regid")
    public String regid;

    @Column(name = "regdate")
    public Timestamp regdate;

    @Column(name = "updid")
    public String updid;

    @Column(name = "upddate")
    public Timestamp upddate;

    @Version
    @Column(name = "version")
    public Integer version;
}
