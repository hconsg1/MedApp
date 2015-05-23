drop database if exists result;
CREATE database IF NOT EXISTS result;
USE result;
CREATE TABLE if not exists patient(
	pid int(11) primary key auto_increment,
	patient_number varchar(50) not null, 
	q1 integer not null, 
	q2 integer not null,
	q3 integer not null,
	q4 integer not null,
	q5 integer not null,
	q6 integer not null,
	q7 integer not null,
	q8 integer not null,
	q9 integer not null,
	q10 integer not null
);

create table if not exists user(
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	patient_num varchar(100) not null
);
