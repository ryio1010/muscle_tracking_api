package com.muscle_tracking_api.MuscleTrackingApi.entity.user;

import com.muscle_tracking_api.MuscleTrackingApi.common.entity.BaseColumnEntity;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Entity
@Getter
@Setter
@Table(name = "m_user")
public class User extends BaseColumnEntity {

    @Id
    @Column(name = "userid")
    public String userId;

    @Column(name = "username")
    public String userNane;

    @Column(name = "password")
    public String password;
}
