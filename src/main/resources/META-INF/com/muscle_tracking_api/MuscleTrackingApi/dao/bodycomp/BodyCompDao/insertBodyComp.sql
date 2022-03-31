INSERT INTO t_bodycomp
(
bodycompid,
height,
weight,
bfp,
bodycompdate,
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
/* bodyComp.height */'',
/* bodyComp.weight */'',
/* bodyComp.bfp */'',
/* bodyComp.bodyCompDate */'',
/* bodyComp.userId */'',
/* bodyComp.userId */'',
CURRENT_TIMESTAMP,
/* bodyComp.userId */'',
CURRENT_TIMESTAMP,
1
)
;