package com.muscle_tracking_api.MuscleTrackingApi.entity;

import com.muscle_tracking_api.MuscleTrackingApi.common.entity.BaseColumnEntity;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Getter
@Setter
@Table(name = "m_user")
public class User extends BaseColumnEntity {
    @Id
    @Column(name = "uid")
    public String uid;

    @Column(name = "uname")
    public String userNane;

    @Column(name = "password")
    public  String password;
}
