select
    m1.menuid,
    m1.menuname,
    m1.musclepartid,
    m2.musclepartname,
    m1.regid,
    m1.regdate,
    m1.updid,
    m1.upddate
from
    m_menu m1
inner join
    m_musclepart m2
on
    m1.musclepartid = m2.musclepartid
;