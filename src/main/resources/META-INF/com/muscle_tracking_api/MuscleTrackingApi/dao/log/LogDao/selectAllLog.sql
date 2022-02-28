select
    t.id,
    m.menuname,
    t.trainingweight,
    t.trainingcount,
    t.trainingdate
from
    t_traininglog t
inner join
    m_menu m
on
    m.menuid = t.menuid
where
    t.userid = /* userId */'ryio1010'
;