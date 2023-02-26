-- drop & create database

drop database if exists lesson2;
create database lesson2;

-- drop & create user
drop user if exists 'lesson2'@'localhost';
create user 'lesson2'@'localhost' identified by 'lesson2';

-- grant privileges to user
grant all privileges on lesson2.* to 'lesson2'@'localhost';

-- create table
use lesson2;

create table student (
	id integer auto_increment primary key,
	name varchar(40) not null,
	phone varchar(12) not null,
	email varchar(40) not null
);