select
    t.logid,
    t.menuid,
    m.menuname,
    t.trainingweight,
    t.trainingcount,
    t.trainingdate,
    t.trainingmemo,
    t.userid,
    t.regid,
    t.regdate,
    t.updid,
    t.upddate,
    t.version
from
    t_traininglog t
inner join
    m_menu m
on
    m.menuid = t.menuid
where
    t.logid = /* logId */''
;