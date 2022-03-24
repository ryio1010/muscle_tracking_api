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

    // 登録者ID
    @Column(name = "regid")
    public String regId;

    // 登録日
    @Column(name = "regdate")
    public Timestamp regDate;

    // 更新者ID
    @Column(name = "updid")
    public String updId;

    // 更新日
    @Column(name = "upddate")
    public Timestamp updDate;

    // バージョン
    @Version
    @Column(name = "version")
    public Integer version;
}
