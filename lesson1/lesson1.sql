drop database if exists lesson1;

create database lesson1;

drop user 'lesson1'@'localhost';

create user 'lesson1'@'localhost' identified by 'lesson1';

grant all privileges on lesson1.* to 'lesson1'@'localhost';

use lesson1;

create table state_type (
	name varchar(40) primary key,
	burmeseName varchar(80)
);

create table state (
	id integer auto_increment primary key,
	name varchar(40) unique not null,
	capital varchar(40) not null,
	region varchar(40) not null
);