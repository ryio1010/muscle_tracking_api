package com.muscle_tracking_api.MuscleTrackingApi.entity.menu;

import com.muscle_tracking_api.MuscleTrackingApi.common.entity.BaseColumnEntity;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Entity
@Getter
@Setter
@Table(name = "m_menu")
public class Menu extends BaseColumnEntity {

    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "m_menu_menuid_seq")
    @Column(name = "menuid")
    public Integer menuId;

    @Column(name = "menuname")
    public String menuName;

    @Column(name = "musclepartid")
    public String musclePartId;

    @Column(name = "musclepartname")
    public String musclePartName;
}
