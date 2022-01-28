CREATE TABLE m_user (
	uid varchar(12) primary key,
	uname varchar(12) not null,
	password varchar(12) not null,
	regid varchar(12),
	regdate timestamp,
	updid varchar(12),
	upddate timestamp,
	version integer
);