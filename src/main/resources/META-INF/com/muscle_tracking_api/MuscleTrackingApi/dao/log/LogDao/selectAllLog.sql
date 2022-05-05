select
    t.logid,
    t.menuid,
    m1.menuname,
    m2.musclepartname,
    t.trainingweight,
    t.trainingcount,
    t.trainingdate,
    t.trainingmemo
from
    t_traininglog t
inner join
    m_menu m1
on
    m1.menuid = t.menuid
inner join
    m_musclepart m2
on
    m1.musclepartid = m2.musclepartid
where
    t.userid = /* userId */'ryio1010'
;