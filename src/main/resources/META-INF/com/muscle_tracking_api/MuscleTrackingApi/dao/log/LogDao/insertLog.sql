INSERT INTO t_traininglog
(
logid,
menuid,
trainingweight,
trainingcount,
trainingdate,
trainingmemo,
userid,
regid,
regdate,
updid,
upddate,
version
)
VALUES
(
default,
/* log.menuId */'',
/* log.trainingWeight */'',
/* log.trainingCount */'',
/* log.trainingDate */'',
/* log.trainingMemo */'',
/* log.userId */'',
/* log.userId */'',
CURRENT_TIMESTAMP,
/* log.userId */'',
CURRENT_TIMESTAMP,
1
)
;